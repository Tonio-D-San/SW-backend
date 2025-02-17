package it.asansonne.storybe.containers.keycloack;

import static io.smallrye.config.ConfigLogging.log;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import it.cybsec.app.ApplicationTests;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;

@EnableConfigurationProperties(value = KeycloakConfigTest.class)
@TestPropertySource("classpath:application-test.properties")
public abstract class ContainersBuilder implements ApplicationTests {
    @Autowired
    private KeycloakConfigTest keycloakConfigTest;
    @Autowired
    protected TestRestTemplate restTemplate;

    //    KEYCLOAK
    @DynamicPropertySource
    static void registerResourceServerIssuerProperty(DynamicPropertyRegistry registry) {
        registry.add("spring.security.oauth2.resourceserver.jwt.issuer-uri", () ->
                keycloakContainer.getAuthServerUrl() + "realms/cyber");
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    static KeycloakContainer keycloakContainer =
            new KeycloakContainer("quay.io/keycloak/keycloak:23.0.1")
                    .withRealmImportFile("keycloak/test-realm.json");


    //    POSTGRES
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.6")
            .withDatabaseName("cyberstackTest")
            .withUsername("postgres")
            .withPassword("postgres")
            .withClasspathResourceMapping(
                    "databases/",
                    "/docker-entrypoint-initdb.d",
                    BindMode.READ_WRITE);

    static {
        postgres.start();
    }

    static {
        keycloakContainer.start();
    }

    protected String retrieveToken() {
        try (Keycloak keycloakAdminClient = KeycloakBuilder.builder()
                .serverUrl(keycloakContainer.getAuthServerUrl())
                .realm(keycloakConfigTest.getRealm())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(keycloakConfigTest.getClientid())
                .scope(keycloakConfigTest.getScope())
                .clientSecret(keycloakConfigTest.getSecret())
                .build()) {

            String access_token = keycloakAdminClient.tokenManager().getAccessToken().getToken();

            return "Bearer " + access_token;
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @BeforeEach
    protected void configureRestTemplate() {
        restTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders().set("Authorization", this.retrieveToken());
                    request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    return execution.execute(request, body);
                })
        );
    }
}

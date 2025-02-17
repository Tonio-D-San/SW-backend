package it.asansonne.storybe.containers.keycloack;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "keycloak.test")
@Getter
@Setter
public class KeycloakConfigTest {
    private String realm;
    private String clientid;
    private String scope;
    private String image;
    private String realmfile;
    private String secret;
}
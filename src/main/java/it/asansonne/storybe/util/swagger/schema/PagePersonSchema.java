package it.asansonne.storybe.util.swagger.schema;

import it.asansonne.storybe.dto.response.PersonResponse;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * The type Page Person schema.
 * Don't use this class into the project
 * It is only a schema for Swagger documentation.
 */
public final class PagePersonSchema extends PageImpl<PersonResponse> {

  /**
   * Instantiates a new Page person schema.
   *
   * @param content  the content
   * @param pageable the pageable
   * @param total    the total
   */
  @SuppressWarnings("unused")
  public PagePersonSchema(List<PersonResponse> content, Pageable pageable, long total) {
    super(content, pageable, total);
  }

  /**
   * Instantiates a new Page person schema.
   *
   * @param content the content
   */
  @SuppressWarnings("unused")
  public PagePersonSchema(List<PersonResponse> content) {
    super(content);
  }
}

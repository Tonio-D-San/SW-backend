package it.asansonne.storybe.util.swagger.schema;

import it.asansonne.storybe.dto.response.TopicResponse;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * The type Page Topic schema.
 * Don't use this class into the project
 * It is only a schema for Swagger documentation.
 */
public class PageTopicSchema extends PageImpl<TopicResponse> {

  /**
   * Instantiates a new Page topic schema.
   *
   * @param content  the content
   * @param pageable the pageable
   * @param total    the total
   */
  @SuppressWarnings("unused")
  public PageTopicSchema(List<TopicResponse> content, Pageable pageable, long total) {
    super(content, pageable, total);
  }

  /**
   * Instantiates a new Page topic schema.
   *
   * @param content the content
   */
  @SuppressWarnings("unused")
  public PageTopicSchema(List<TopicResponse> content) {
    super(content);
  }
}

package it.asansonne.storybe.util.swagger.schema;

import it.asansonne.storybe.dto.response.StoryResponse;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * The type Page Story schema.
 * Don't use this class into the project
 * It is only a schema for Swagger documentation.
 */
public class PageStorySchema extends PageImpl<StoryResponse> {

  /**
   * Instantiates a new Page story schema.
   *
   * @param content  the content
   * @param pageable the pageable
   * @param total    the total
   */
  @SuppressWarnings("unused")
  public PageStorySchema(List<StoryResponse> content, Pageable pageable, long total) {
    super(content, pageable, total);
  }

  /**
   * Instantiates a new Page story schema.
   *
   * @param content the content
   */
  @SuppressWarnings("unused")
  public PageStorySchema(List<StoryResponse> content) {
    super(content);
  }
}

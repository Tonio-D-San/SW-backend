package it.asansonne.storybe.util.swagger.schema;

import it.asansonne.storybe.dto.response.MasterResponse;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * The type Page Master schema.
 * Don't use this class into the project
 * It is only a schema for Swagger documentation.
 */
public final class PageMasterSchema extends PageImpl<MasterResponse> {

  /**
   * Instantiates a new Page master schema.
   *
   * @param content  the content
   * @param pageable the pageable
   * @param total    the total
   */
  @SuppressWarnings("unused")
  public PageMasterSchema(List<MasterResponse> content, Pageable pageable, long total) {
    super(content, pageable, total);
  }

  /**
   * Instantiates a new Page master schema.
   *
   * @param content the content
   */
  @SuppressWarnings("unused")
  public PageMasterSchema(List<MasterResponse> content) {
    super(content);
  }
}

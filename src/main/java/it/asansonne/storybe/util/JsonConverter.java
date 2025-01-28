package it.asansonne.storybe.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * The type Json converter.
 */
@Converter
public class JsonConverter implements AttributeConverter<Object, String> {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(Object attribute) {
    try {
      return objectMapper.writeValueAsString(attribute);
    } catch (Exception e) {
      throw new IllegalArgumentException("Errore durante la serializzazione JSON", e);
    }
  }

  @Override
  public Object convertToEntityAttribute(String dbData) {
    try {
      return objectMapper.readValue(dbData, Object.class);
    } catch (Exception e) {
      throw new IllegalArgumentException("Errore durante la deserializzazione JSON", e);
    }
  }
}

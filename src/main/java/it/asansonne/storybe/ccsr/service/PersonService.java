package it.asansonne.storybe.ccsr.service;

import it.asansonne.storybe.model.jpa.Person;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface Person service.
 */
public interface PersonService {

  /**
   * Find person by email optional.
   *
   * @param email the email
   * @return the optional
   */
  Optional<Person> findPersonByEmail(String email);

  /**
   * Find person by uuid optional.
   *
   * @param personUuid the person uuid
   * @return the optional
   */
  Optional<Person> findPersonByUuid(UUID personUuid);

  /**
   * Find all persons page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Person> findAllPersons(Pageable pageable);

  /**
   * Find active persons page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Person> findActivePersons(Pageable pageable);

  /**
   * Find inactive persons page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Person> findInactivePersons(Pageable pageable);

  /**
   * Update person.
   *
   * @param person the person
   * @return the person
   */
  Person updatePerson(Person person);
}

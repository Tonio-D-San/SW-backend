package it.asansonne.storybe.ccsr.repository.jpa;

import it.asansonne.storybe.model.jpa.Person;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Person repository.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

  /**
   * Find person by uuid optional.
   *
   * @param uuid the uuid
   * @return the optional
   */
  Optional<Person> findPersonByUuid(UUID uuid);

  /**
   * Find by email optional.
   *
   * @param email the email
   * @return the optional
   */
  Optional<Person> findByEmail(String email);

  /**
   * Find all by is active true page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Person> findAllByIsActiveTrue(Pageable pageable);

  /**
   * Find all by is active false page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Person> findAllByIsActiveFalse(Pageable pageable);
}

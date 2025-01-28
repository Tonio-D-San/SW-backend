package it.asansonne.storybe.ccsr.service.impl;

import static it.asansonne.storybe.constant.MessageConstant.PERSON_ACTIVE_EMPTY;
import static it.asansonne.storybe.constant.MessageConstant.PERSON_EMPTY;
import static it.asansonne.storybe.constant.MessageConstant.PERSON_INACTIVE_EMPTY;

import it.asansonne.storybe.ccsr.repository.jpa.PersonRepository;
import it.asansonne.storybe.ccsr.service.PersonService;
import it.asansonne.storybe.model.jpa.Person;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * The type Person service.
 */
@Service
@AllArgsConstructor
public final class PersonServiceImpl implements PersonService {
  private final PersonRepository personRepository;

  @Override
  public Optional<Person> findPersonByEmail(String email) {
    return personRepository.findByEmail(email);
  }

  @Override
  public Optional<Person> findPersonByUuid(UUID personUuid) {
    return personRepository.findPersonByUuid(personUuid);
  }

  @Override
  public Page<Person> findAllPersons(Pageable pageable) {
    Page<Person> persons = personRepository.findAll(pageable);
    if (persons.isEmpty()) {
      throw new EntityNotFoundException(PERSON_EMPTY);
    }
    return persons;
  }

  @Override
  public Page<Person> findActivePersons(Pageable pageable) {
    Page<Person> persons = personRepository.findAllByIsActiveTrue(pageable);
    if (persons.isEmpty()) {
      throw new EntityNotFoundException(PERSON_ACTIVE_EMPTY);
    }
    return persons;
  }

  @Override
  public Page<Person> findInactivePersons(Pageable pageable) {
    Page<Person> persons = personRepository.findAllByIsActiveFalse(pageable);
    if (persons.isEmpty()) {
      throw new EntityNotFoundException(PERSON_INACTIVE_EMPTY);
    }
    return persons;
  }

  @Override
  public Person updatePerson(Person person) {
    return personRepository.save(person);
  }
}

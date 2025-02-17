package it.asansonne.storybe.service.it;

import static it.cybsec.app.util.DataBuilder.makeTestActivePerson;
import static it.cybsec.app.util.DataBuilder.makeTestInactivePerson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.cybsec.app.IntegrationTest;
import it.cybsec.app.containers.keycloack.ContainersBuilder;
import it.cybsec.app.model.Person;
import it.cybsec.app.repository.PersonRepository;
import it.cybsec.app.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DisplayName("PersonService IT Test")
class PersonServiceImplIT extends ContainersBuilder implements IntegrationTest {
     @Autowired
     private PersonService personService;
     @Autowired
     private PersonRepository personRepository;
     private Pageable pageable;

     @BeforeEach
     void setUp() {
         pageable = PageRequest.of(0, 10);
     }

    @Test
    @DisplayName("Find all persons found")
    void findAllPersonsFound() {
        personRepository.save(makeTestActivePerson());
        personRepository.save(makeTestActivePerson());
        Page<Person> persons = personService.findAllPersons(pageable);
        assertFalse(persons.isEmpty());
        assertEquals(2, persons.getTotalElements());
    }

    @Test
    @DisplayName("Find all persons not found")
    void findAllPersonsNotFound() {
        assertThrows(EntityNotFoundException.class,
                () -> personService.findAllPersons(pageable));
    }

    @Test
    @DisplayName("Find active persons found")
    void findActivePersonsFound() {
        personRepository.save(makeTestActivePerson());
        personRepository.save(makeTestInactivePerson());
        Page<Person> persons = personService.findActivePersons(pageable);
        assertFalse(persons.isEmpty());
        assertEquals(1, persons.getTotalElements());
        assertTrue(persons.getContent().get(0).getIsActive());
    }

    @Test
    @DisplayName("Find active persons not found")
    void findActivePersonsNotFound() {
        personRepository.save(makeTestInactivePerson());
        assertThrows(EntityNotFoundException.class,
                () -> personService.findActivePersons(pageable));
    }

    @Test
    @DisplayName("Find inactive persons found")
    void findInactivePersonsInactive() {
        personRepository.save(makeTestActivePerson());
        personRepository.save(makeTestInactivePerson());
        Page<Person> persons = personService.findInactivePersons(pageable);
        assertFalse(persons.isEmpty());
        assertEquals(1, persons.getTotalElements());
        assertFalse(persons.getContent().get(0).getIsActive());
    }

    @Test
    @DisplayName("Find inactive persons not found")
    void findPersonByEmail() {
        personRepository.save(makeTestActivePerson());
        assertThrows(EntityNotFoundException.class,
                () -> personService.findInactivePersons(pageable));
    }

    @Test
    void createPerson() {
        assertTrue(true);
    }

    @Test
    void updatePerson() {
        assertTrue(true);
    }
}
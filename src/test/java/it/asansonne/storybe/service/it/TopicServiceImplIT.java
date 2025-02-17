package it.asansonne.storybe.service.it;

import static it.cybsec.app.util.DataBuilder.makeTestActivePerson;
import static it.cybsec.app.util.DataBuilder.makeTestCategory;
import static it.cybsec.app.util.DataBuilder.makeTestTopic;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.cybsec.app.IntegrationTest;
import it.cybsec.app.containers.keycloack.ContainersBuilder;
import it.cybsec.app.model.Category;
import it.cybsec.app.model.Person;
import it.cybsec.app.model.Topic;
import it.cybsec.app.repository.PersonRepository;
import it.cybsec.app.service.CategoryService;
import it.cybsec.app.service.TopicService;
import jakarta.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DisplayName("TopicService IT Test")
class TopicServiceImplIT extends ContainersBuilder implements IntegrationTest {
    @Autowired
    private TopicService topicService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CategoryService categoryService;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        pageable = PageRequest.of(0, 10);
    }

    @Test
    @DisplayName("Find all topics")
    void findAllActiveTopics() {
        topicService.createTopic(makeTestTopic(personRepository.save(makeTestActivePerson())));
        topicService.createTopic(makeTestTopic(personRepository.save(makeTestActivePerson())));
        Page<Topic> topics = topicService.findActiveTopics(pageable);
        assertFalse(topics.isEmpty());
        assertEquals(2, topics.getTotalElements());
    }

    @Test
    @DisplayName("Find topic by creation date and title")
    void findTopicByCreationDateAndTitle() {
        Topic topic = topicService.createTopic(makeTestTopic(personRepository.save(makeTestActivePerson())));
        Optional<Topic> foundTopic = topicService.findTopicByUuid(topic.getUuid());
        assertTrue(foundTopic.isPresent());
        assertAll( "Topic Validation",
            () -> assertEquals(topic.getTitle(), foundTopic.get().getTitle()),
            () -> assertEquals(topic.getCreationDate(), foundTopic.get().getCreationDate())
        );
    }

    @Test
    @DisplayName("Create topic")
    void createTopic() {
        topicService.createTopic(makeTestTopic(personRepository.save(makeTestActivePerson())));
        assertEquals(1, topicService.findActiveTopics(pageable).getTotalElements());
    }

    @Test
    @DisplayName("Delete topic")
    void deleteTopic() {
        Topic topic1 = topicService.createTopic(makeTestTopic(personRepository.save(makeTestActivePerson())));
        assertEquals(1,topicService.findActiveTopics(pageable).getTotalElements());
        topicService.deleteTopicByUuid(topic1.getUuid());
        assertThrows(EntityNotFoundException.class,() -> topicService.findActiveTopics(pageable));
    }

    @Test
    @DisplayName("Update topic")
    void updateTopic() {
        Topic topic1 = topicService.createTopic(makeTestTopic(personRepository.save(makeTestActivePerson())));
        topic1.setCreationDate(Instant.now().toEpochMilli());
        topic1.setLastEditDate(Instant.now().plusSeconds(864000).toEpochMilli());
        topicService.updateTopic(topic1);
        assertNotEquals(topic1.getCreationDate(), topic1.getLastEditDate());

    }

    @Test
    @DisplayName("Find all topic by category")
    void findAllTopicsByCategory() {
        Person person = personRepository.save(makeTestActivePerson());
        List<Category> categories = new ArrayList<>();
        categories.add(categoryService.createCategory(makeTestCategory()));
        categories.add(categoryService.createCategory(makeTestCategory()));
        topicService.createTopic(makeTestTopic(person)).setCategories(categories);
        topicService.createTopic(makeTestTopic(person)).setCategories(categories);
        Page<Topic> topics = topicService.findAllTopicsByCategory(categories.get(0).getUuid(), pageable);
        assertFalse(topics.isEmpty());
        assertEquals(2, topics.getTotalElements());
    }

    @Test
    @DisplayName("Find all topic by author")
    void findAllTopicByAuthor() {
        Topic topic1 = topicService.createTopic(makeTestTopic(personRepository.save(makeTestActivePerson())));
        Topic topic2 = topicService.createTopic(makeTestTopic(personRepository.save(makeTestActivePerson())));
        Page<Topic> topics = topicService.findAllTopicByAuthor(topic1.getAuthor(), pageable);
        assertFalse(topics.isEmpty());
        assertEquals(1, topics.getTotalElements());
        assertEquals(topic1.getAuthor(), topics.getContent().get(0).getAuthor());
    }

    @Test
    @DisplayName("Find last added topic")
    void findLastAddedTopic() {
        assertThrows(EntityNotFoundException.class,
                () -> topicService.findLastAddedTopic());
        Topic topic1 = topicService.createTopic(makeTestTopic(personRepository.save(makeTestActivePerson())));
        Topic topic2 = topicService.createTopic(makeTestTopic(personRepository.save(makeTestActivePerson())));
        topic2.setLastEditDate(Instant.now().plusSeconds(3600).toEpochMilli());
        assertEquals(topic2, topicService.findLastAddedTopic());
        assertNotEquals(topic1, topicService.findLastAddedTopic());
    }

    @Test
    @DisplayName("Find topics by title containing")
    void findTopicsByTitleContaining() {
        Topic topic1 = topicService.createTopic(makeTestTopic(personRepository.save(makeTestActivePerson())));
        assertEquals(topic1.getTitle(),
                topicService.findTopicsByTitleContaining("top", pageable).getContent().get(0).getTitle());
        assertEquals(topic1.getTitle(),
                topicService.findTopicsByTitleContaining("pic", pageable).getContent().get(0).getTitle());
        assertThrows(EntityNotFoundException.class,
                () -> topicService.findTopicsByTitleContaining("ics", pageable));
    }
}
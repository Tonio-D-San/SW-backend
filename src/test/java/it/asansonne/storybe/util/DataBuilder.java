package it.asansonne.storybe.util;

import static it.cybsec.app.util.generator.NameSelector.getRandomPersonalName;

import it.cybsec.app.model.Attachment;
import it.cybsec.app.model.Category;
import it.cybsec.app.model.Comment;
import it.cybsec.app.model.Person;
import it.cybsec.app.model.Topic;
import java.time.Instant;
import java.util.UUID;

public class DataBuilder {
  public static Category makeTestCategory() {
    return Category.builder()
        .uuid(UUID.randomUUID())
        .name("Test category " + getRandomPersonalName())
        .build();
  }

  public static Topic makeTestTopic(Person person) {
    return Topic.builder()
        .uuid(UUID.randomUUID())
        .title("Test Topic " + getRandomPersonalName())
        .problem("Test Problem")
        .creationDate(Instant.now().toEpochMilli())
        .author(person)
        .isActive(true)
        .isOpen(true)
        .build();
  }

  public static Comment makeTestComment(Topic topic, Person person) {
    return Comment.builder()
        .uuid(UUID.randomUUID())
        .creationDate(Instant.now().toEpochMilli())
        .lastEditDate(Instant.now().toEpochMilli())
        .description("Test Comment")
        .parent(null)
        .author(person)
        .isActive(true)
        .topic(topic)
        .topicIdForBestComment(topic)
        .build();
  }

  public static Comment makeChildrenComment(Comment parentComment, Topic topic, Person person) {
    return Comment.builder()
        .uuid(UUID.randomUUID())
        .creationDate(Instant.now().plusSeconds(864000).toEpochMilli())
        .description("Child Comment")
        .author(person)
        .topic(topic)
        .parent(parentComment)
        .isActive(true)
        .topicIdForBestComment(topic)
        .build();
  }

  public static Person makeTestActivePerson() {
    Person person =
        Person.builder()
            .uuid(UUID.randomUUID())
            .name(getRandomPersonalName())
            .surname("Viola")
            .isActive(true).build();
    person.setEmail(
        person.getName().substring(0, 3).toLowerCase()
            + person.getSurname().toLowerCase() +
            "@cybsec.it");
    return person;
  }

  public static Person makeTestInactivePerson() {
    Person person =
        Person.builder()
            .uuid(UUID.randomUUID())
            .name(getRandomPersonalName())
            .surname("Viola")
            .isActive(false)
            .build();
    person.setEmail(
        person.getName().substring(0, 3).toLowerCase()
            + person.getSurname().toLowerCase() +
            "@cybsec.it");
    return person;
  }

  public static Attachment makeTestAttachment(Topic topic) {
    return Attachment.builder()
        .uuid(UUID.randomUUID())
        .name("testFile.txt")
        .path("/encryptedTopicId/testFile.txt")
        .topic(topic)
        .build();
  }
}
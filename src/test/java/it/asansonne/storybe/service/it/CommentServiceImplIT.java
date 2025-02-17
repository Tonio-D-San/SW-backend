package it.asansonne.storybe.service.it;

import static it.cybsec.app.util.DataBuilder.makeChildrenComment;
import static it.cybsec.app.util.DataBuilder.makeTestActivePerson;
import static it.cybsec.app.util.DataBuilder.makeTestComment;
import static it.cybsec.app.util.DataBuilder.makeTestTopic;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.cybsec.app.IntegrationTest;
import it.cybsec.app.containers.keycloack.ContainersBuilder;
import it.cybsec.app.model.Comment;
import it.cybsec.app.model.Person;
import it.cybsec.app.model.Topic;
import it.cybsec.app.repository.PersonRepository;
import it.cybsec.app.repository.TopicRepository;
import it.cybsec.app.service.CommentService;
import it.cybsec.app.service.TopicService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DisplayName("CommentService IT Test")
class CommentServiceImplIT extends ContainersBuilder implements IntegrationTest {
  @Autowired
  private CommentService commentService;
  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private TopicRepository topicRepository;
  @Autowired
  private TopicService topicService;

  @Test
  @DisplayName("Delete comment")
  void deleteComment() {
    Person person = makeTestActivePerson();
    personRepository.save(person);
    Topic topic = makeTestTopic(person);
    topicService.createTopic(topic);
    Comment comment = makeTestComment(topic, person);
    commentService.createComment(comment);
    commentService.deleteCommentByUuid(comment.getUuid());
    assertThrows(EntityNotFoundException.class,
        () -> commentService.findCommentByUuid(comment.getUuid()));
  }

  @Test
  @DisplayName("Find comment by creation date")
  void findCommentByCreationDate() {
    Person person = makeTestActivePerson();
    personRepository.save(person);
    Topic topic = makeTestTopic(person);
    topicService.createTopic(topic);
    Comment comment = makeTestComment(topic, person);
    commentService.createComment(comment);
    Optional<Comment> foundComment = commentService.findCommentByUuid(comment.getUuid());
    assertTrue(foundComment.isPresent());
    assertEquals("Test Comment", foundComment.get().getDescription());
  }

  @Test
  @DisplayName("Find all comments by topic Ok")
  void findAllCommentsByTopicOk() {
    Person person = makeTestActivePerson();
    personRepository.save(person);
    Topic topic = makeTestTopic(person);
    topicService.createTopic(topic);
    Comment comment = makeTestComment(topic, person);
    commentService.createComment(comment);
    Page<Comment> comments =
        commentService.findAllCommentsByTopicUuid(topic.getUuid(), PageRequest.of(0, 10));
    assertFalse(comments.isEmpty());
    assertEquals("Test Comment", comments.getContent().get(0).getDescription());
  }

  @Test
  @DisplayName("Find all comments by topic not found")
  void findAllCommentsByTopicNotFound() {
    Person person = makeTestActivePerson();
    personRepository.save(person);
    Topic topic = makeTestTopic(person);
    topicService.createTopic(topic);
    assertThrows(EntityNotFoundException.class,
        () -> commentService.findAllCommentsByTopicUuid(topic.getUuid(), PageRequest.of(0, 10)));
  }

  @Test
  @DisplayName("Find all parent comments by topic ok")
  void findAllParentCommentsByTopicOk() {
    Person person = personRepository.save(makeTestActivePerson());
    Topic topic = topicRepository.save(makeTestTopic(person));
    Comment comment = makeTestComment(topic, person);
    commentService.createComment(comment);
    Page<Comment> comments =
        commentService.findAllParentCommentsByTopicUuid(topic.getUuid(), PageRequest.of(0, 10));
    assertFalse(comments.isEmpty());
    assertEquals("Test Comment", comments.getContent().get(0).getDescription());
    assertNull(comments.getContent().get(0).getChildren());
  }

  @Test
  @DisplayName("Find all parent comments by topic not found")
  void findAllParentCommentsByTopicNotFound() {
    Person person = makeTestActivePerson();
    personRepository.save(person);
    Topic topic = makeTestTopic(person);
    topicService.createTopic(topic);
    assertThrows(EntityNotFoundException.class,
        () -> commentService.findAllParentCommentsByTopicUuid(topic.getUuid(),
            PageRequest.of(0, 10)));
  }

  @Test
  @DisplayName("Find all child comments by parent ok")
  void findAllChildCommentsByParentOk() {
    Person person = makeTestActivePerson();
    personRepository.save(person);
    Topic topic = makeTestTopic(person);
    topicService.createTopic(topic);
    Comment parentComment = makeTestComment(topic, person);
    commentService.createComment(parentComment);
    Comment childComment = makeChildrenComment(parentComment, topic, person);
    commentService.createComment(childComment);
    Page<Comment> childComments =
        commentService.findAllChildCommentsByParent(parentComment, PageRequest.of(0, 10));
    assertFalse(childComments.isEmpty());
    assertEquals("Child Comment", childComments.getContent().get(0).getDescription());
  }

  @Test
  @DisplayName("Find all child comments by parent not found")
  void findAllChildCommentsByParentNotFound() {
    Person person = makeTestActivePerson();
    personRepository.save(person);
    Topic topic = makeTestTopic(person);
    topicService.createTopic(topic);
    Comment parentComment = makeTestComment(topic, person);
    commentService.createComment(parentComment);
    assertThrows(EntityNotFoundException.class, () ->
        commentService.findAllChildCommentsByParent(parentComment, PageRequest.of(0, 10)));
  }

}
package it.asansonne.storybe.service.it;

import static it.cybsec.app.util.DataBuilder.makeTestActivePerson;
import static it.cybsec.app.util.DataBuilder.makeTestAttachment;
import static it.cybsec.app.util.DataBuilder.makeTestTopic;
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.cybsec.app.IntegrationTest;
import it.cybsec.app.containers.keycloack.ContainersBuilder;
import it.cybsec.app.model.Attachment;
import it.cybsec.app.model.Topic;
import it.cybsec.app.repository.AttachmentRepository;
import it.cybsec.app.service.TopicService;
import it.cybsec.app.service.impl.AttachmentServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.web.multipart.MultipartFile;

@DisplayName("AttachmentService IT Test")
class AttachmentServiceImplIT extends ContainersBuilder implements IntegrationTest {
  @Autowired
  private AttachmentServiceImpl attachmentService;
  @MockBean
  private AttachmentRepository attachmentRepository;
  @MockBean
  private TopicService topicService;
  @MockBean
  private SftpRemoteFileTemplate sftpRemoteFileTemplate;
  @Mock
  private MultipartFile multipartFile;

  @Test
  void testUploadSuccess() throws Exception {
    Topic topic = topicService.createTopic(makeTestTopic(makeTestActivePerson()));
    Mockito.when(topicService.findTopicByUuid(topic.getUuid()))
        .thenReturn(Optional.of(topic));
    Mockito.when(multipartFile.getOriginalFilename()).thenReturn("testFile.txt");
    attachmentService.upload(multipartFile, topic);
    Mockito.verify(attachmentRepository, Mockito.times(1))
        .save(Mockito.any(Attachment.class));
  }

  @Test
  void testDownloadFileNotFound() {
    Mockito.when(attachmentRepository.findByNameAndPath(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(Optional.empty());
    assertThrows(EntityNotFoundException.class,
        () -> attachmentService.download("/nonexistent/path", "/local/path"));
  }

  @Test
  void testDownloadSuccess() {
    Mockito.when(attachmentRepository.findByNameAndPath(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(Optional.of(makeTestAttachment(makeTestTopic(makeTestActivePerson()))));
    Mockito.when(sftpRemoteFileTemplate.execute(Mockito.any())).thenReturn(true);
    attachmentService.download("/encryptedTopicId/testFile.txt", "/local/path");
    Mockito.verify(sftpRemoteFileTemplate).execute(Mockito.any());
  }
}
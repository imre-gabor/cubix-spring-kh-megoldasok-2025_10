package hu.webuni.university.service;

import java.util.Random;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import hu.webuni.eduservice.wsclient.StudentXmlWsImplService;
import hu.webuni.jms.dto.FreeSemesterRequest;
import hu.webuni.university.aspect.Retry;
import jakarta.jms.Topic;
import lombok.RequiredArgsConstructor;

@Service
@Retry(times = 4, waitTime = 500)
@RequiredArgsConstructor
public class CentralEducationService {

	private final JmsTemplate educationJmsTemplate;
	
	public int getNumFreeSemestersForStudent(int eduId) {
		return new StudentXmlWsImplService()
				.getStudentXmlWsImplPort()
				.getNumFreeSemestersForStudent(eduId);
	}
	
	public void askNumFreeSemestersForStudent(int eduId) {
		
		Topic replyTopic = educationJmsTemplate.execute(session -> session.createTopic("free_semester_responses"));
		FreeSemesterRequest freeSemesterRequest = new FreeSemesterRequest();
		freeSemesterRequest.setStudentId(eduId);
		educationJmsTemplate.convertAndSend("free_semester_requests", freeSemesterRequest, message -> {
			message.setJMSReplyTo(replyTopic);
			return message;
		});
	}


}

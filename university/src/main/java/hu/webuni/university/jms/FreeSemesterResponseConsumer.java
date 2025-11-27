package hu.webuni.university.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import hu.webuni.jms.dto.FreeSemesterRequest;
import hu.webuni.jms.dto.FreeSemesterResponse;
import hu.webuni.university.service.StudentService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FreeSemesterResponseConsumer {

	private final StudentService studentService;
	
	@JmsListener(destination = "free_semester_responses", containerFactory = "educationFactory")
	public void onDelayMessage(FreeSemesterResponse freeSemesterResponse) {
		
		studentService.updateFreeSemesters(freeSemesterResponse.getStudentId(), freeSemesterResponse.getNumFreeSemesters());
	}
}

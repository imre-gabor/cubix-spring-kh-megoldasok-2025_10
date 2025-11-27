package hu.webuni.university.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import hu.webuni.eduservice.wsclient.StudentXmlWsImplService;
import hu.webuni.university.aspect.Retry;

@Service
@Retry(times = 4, waitTime = 500)
public class CentralEducationService {

	public int getNumFreeSemestersForStudent(int eduId) {
		return new StudentXmlWsImplService()
				.getStudentXmlWsImplPort()
				.getNumFreeSemestersForStudent(eduId);
	}
	public void askNumFreeSemestersForStudent(int eduId) {
		
	}


}

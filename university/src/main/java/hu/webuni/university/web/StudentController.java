package hu.webuni.university.web;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hu.webuni.university.api.StudentControllerApi;
import hu.webuni.university.service.StudentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class StudentController implements StudentControllerApi {
	
	private final StudentService studentService;

	@Override
	public ResponseEntity<Resource> getProfilePicture(Integer id) {
		return ResponseEntity.ok(studentService.getProfilePicture(id));
	}

	@Override
	public ResponseEntity<Void> uploadProfilePicture(Integer id, MultipartFile content) {
		try {
			studentService.saveProfilePicture(id, content.getInputStream());
			return ResponseEntity.ok().build();
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	
	
	

}

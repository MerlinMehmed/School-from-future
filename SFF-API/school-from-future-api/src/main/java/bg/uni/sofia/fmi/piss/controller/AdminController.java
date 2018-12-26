package bg.uni.sofia.fmi.piss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bg.uni.sofia.fmi.piss.dao.SchoolSubjectDAO;
import bg.uni.sofia.fmi.piss.data.SchoolSubject;
import bg.uni.sofia.fmi.piss.dto.Teacher;

@RestController
public class AdminController {
	@Autowired
	SchoolSubjectDAO subjectDAO;

	@RequestMapping(value = "/schools", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public ResponseEntity<List<String>> getSchools() {
		final List<String> schools = subjectDAO.getSchools();
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<List<String>>(schools, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/teachers", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public ResponseEntity<List<Teacher>> getTeachers() {
		final List<Teacher> teachers = subjectDAO.getTeachers();
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<List<Teacher>>(teachers, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/add-subject", method = RequestMethod.POST, produces = { "application/xml",
			"application/json" })
	public void addSubject(@RequestBody SchoolSubject data) {
		System.out.println(data.getName());
		subjectDAO.insertSubject(data.getName(), data.getDescription(), data.getTeacher());
	}
}

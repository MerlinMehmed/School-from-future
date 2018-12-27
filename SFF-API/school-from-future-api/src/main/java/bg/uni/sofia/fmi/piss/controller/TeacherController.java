package bg.uni.sofia.fmi.piss.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bg.uni.sofia.fmi.piss.dao.SchoolSubjectDAO;
import bg.uni.sofia.fmi.piss.dao.StudentGradeDAO;
import bg.uni.sofia.fmi.piss.dao.StudentSubjectDAO;
import bg.uni.sofia.fmi.piss.dao.UserDAO;
import bg.uni.sofia.fmi.piss.data.SchoolSubject;
import bg.uni.sofia.fmi.piss.data.StudentGrade;
import bg.uni.sofia.fmi.piss.dto.User;

@RestController
public class TeacherController {
	@Autowired
	SchoolSubjectDAO subjectDAO;

	@Autowired
	StudentGradeDAO studentGradeDAO;

	@Autowired
	StudentSubjectDAO studentSubjectDAO;

	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/get-subjects/{teacher}", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public ResponseEntity<List<SchoolSubject>> getSubjects(@RequestParam String teacher) {
		final List<SchoolSubject> subjects = subjectDAO.getTeacherSubjects(teacher);
		return new ResponseEntity<List<SchoolSubject>>(subjects, HttpStatus.OK);
	}

	@RequestMapping(value = "/get-students/{subject}", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public ResponseEntity<List<User>> getStudents(@RequestParam int subject) {
		final List<String> studentsEmails = studentSubjectDAO.getStudents(subject);
		final List<User> students = new ArrayList<>();
		for (final String student : studentsEmails) {
			students.add(userDAO.getUserData(student));
		}
		return new ResponseEntity<List<User>>(students, HttpStatus.OK);
	}

	@RequestMapping(value = "/add-grade", method = RequestMethod.POST)
	public void addSubject(@RequestBody StudentGrade data) {
		studentGradeDAO.addGrade(data.getStudent(), data.getSubjectId(), data.getGrade());
	}
}

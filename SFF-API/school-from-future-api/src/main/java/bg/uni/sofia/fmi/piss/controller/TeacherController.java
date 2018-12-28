package bg.uni.sofia.fmi.piss.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bg.uni.sofia.fmi.piss.dao.EventDAO;
import bg.uni.sofia.fmi.piss.dao.SchoolSubjectDAO;
import bg.uni.sofia.fmi.piss.dao.StudentGradeDAO;
import bg.uni.sofia.fmi.piss.dao.StudentSubjectDAO;
import bg.uni.sofia.fmi.piss.dao.UserDAO;
import bg.uni.sofia.fmi.piss.data.SchoolSubject;
import bg.uni.sofia.fmi.piss.data.StudentGrade;
import bg.uni.sofia.fmi.piss.dto.Event;
import bg.uni.sofia.fmi.piss.dto.Student;

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

	@Autowired
	EventDAO eventDAO;

	@RequestMapping(value = "/get-subjects/{teacher}/{email}", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public ResponseEntity<List<SchoolSubject>> getSubjects(
			@PathVariable(name = "teacher", required = true) String teacher,
			@PathVariable(name = "email", required = true) String email) {
		email = email.replace(',', '.');
		final List<SchoolSubject> subjects = subjectDAO.getTeacherSubjects(teacher.concat("@").concat(email));
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<List<SchoolSubject>>(subjects, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/get-students/{subject}", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public ResponseEntity<List<Student>> getStudents(@PathVariable int subject) {
		final List<String> studentsEmails = studentSubjectDAO.getStudents(subject);
		final List<Student> students = new ArrayList<>();
		for (final String student : studentsEmails) {
			students.add(userDAO.getStudentData(student));
		}
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}

	@RequestMapping(value = "/add-grade", method = RequestMethod.POST)
	public void addSubject(@RequestBody StudentGrade data) {
		studentGradeDAO.addGrade(data.getStudent(), data.getSubjectId(), data.getGrade());
	}

	@RequestMapping(value = "/add-event", method = RequestMethod.POST)
	public void addEvent(@RequestBody Event data) {
		eventDAO.addEvent(data.getSubject(), data.getTime(), data.getLatitude(), data.getLongitude());
	}
}

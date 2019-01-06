package bg.uni.sofia.fmi.piss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bg.uni.sofia.fmi.piss.dao.StudentAbsenceDAO;
import bg.uni.sofia.fmi.piss.dao.StudentGradeDAO;
import bg.uni.sofia.fmi.piss.dao.StudentNoteDAO;
import bg.uni.sofia.fmi.piss.dao.StudentSubjectDAO;
import bg.uni.sofia.fmi.piss.dao.UserDAO;
import bg.uni.sofia.fmi.piss.data.SchoolSubject;
import bg.uni.sofia.fmi.piss.dto.Student;

@RestController
public class StudentController {
	@Autowired
	UserDAO userDAO;

	@Autowired
	StudentSubjectDAO studentSubjectDAO;

	@Autowired
	StudentGradeDAO studentGradeDAO;

	@Autowired
	StudentNoteDAO studentNoteDAO;

	@Autowired
	StudentAbsenceDAO studentAbsenceDAO;

	@RequestMapping(value = "/subjects/{student}/{email}", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public ResponseEntity<List<SchoolSubject>> getSubjects(
			@PathVariable(name = "student", required = true) String student,
			@PathVariable(name = "email", required = true) String email) {
		email = email.replace(',', '.');
		final List<SchoolSubject> subjects = studentSubjectDAO.getSubjects(student.concat("@").concat(email));
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<List<SchoolSubject>>(subjects, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/grades/{student}/{email}/{subject}", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public ResponseEntity<List<Integer>> getGrades(@PathVariable(name = "student", required = true) String student,
			@PathVariable(name = "email", required = true) String email,
			@PathVariable(name = "subject", required = true) int subjectId) {
		email = email.replace(',', '.');
		final List<Integer> grades = studentGradeDAO.getGrades(student.concat("@").concat(email), subjectId);
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<List<Integer>>(grades, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/absences/{student}/{email}/{subject}", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public ResponseEntity<Double> getAbsences(@PathVariable(name = "student", required = true) String student,
			@PathVariable(name = "email", required = true) String email,
			@PathVariable(name = "subject", required = true) int subjectId) {
		email = email.replace(',', '.');
		final double absences = studentAbsenceDAO.getAbsences(student.concat("@").concat(email), subjectId);
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<Double>(absences, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/notes/{student}/{email}/{subject}", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public ResponseEntity<List<String>> getNotes(@PathVariable(name = "student", required = true) String student,
			@PathVariable(name = "email", required = true) String email,
			@PathVariable(name = "subject", required = true) int subjectId) {
		email = email.replace(',', '.');
		final List<String> notes = studentNoteDAO.getNotes(student.concat("@").concat(email), subjectId);
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<List<String>>(notes, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/student/{student}/{email}", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public ResponseEntity<Student> getStudentData(@PathVariable(name = "student", required = true) String student,
			@PathVariable(name = "email", required = true) String email) {
		email = email.replace(',', '.');
		final Student st = userDAO.getStudentData(student.concat("@").concat(email));
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return new ResponseEntity<Student>(st, headers, HttpStatus.OK);
	}

}

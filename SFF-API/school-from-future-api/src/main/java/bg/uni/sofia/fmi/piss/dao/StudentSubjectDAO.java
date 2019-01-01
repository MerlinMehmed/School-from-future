package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import bg.uni.sofia.fmi.piss.data.SchoolSubject;

public final class StudentSubjectDAO {
	private static final String GET_STUDENT = "SELECT * FROM student_subject where subject_id = ?;";
	private static final String SELECT_SUBJECTS_BY_STUDENT = "SELECT * FROM student_subject JOIN subject ON "
			+ "student_subject.subject_id=subject.id WHERE student_id=?;";

	@Autowired
	private DataSource dataSource;

	public List<String> getStudents(int subjectId) {
		final List<String> students = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(GET_STUDENT);
			ps.setInt(1, subjectId);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				students.add(rs.getString("student_id"));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting schools");
			e.printStackTrace();
		}
		return students;
	}

	public List<SchoolSubject> getSubjects(String student) {
		final List<SchoolSubject> subjects = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(SELECT_SUBJECTS_BY_STUDENT);
			ps.setString(1, student);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				subjects.add(new SchoolSubject(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getString("teacher_id")));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting subjects");
			e.printStackTrace();
		}
		return subjects;
	}
}

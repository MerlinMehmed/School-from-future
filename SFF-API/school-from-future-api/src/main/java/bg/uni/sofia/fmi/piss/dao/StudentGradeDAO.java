package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public final class StudentGradeDAO {
	private static final String INSERT_GRADE = "INSERT INTO student_grade (student_id, subject_id, grade) VALUES (?,?,?);";

	@Autowired
	private DataSource dataSource;

	public void addGrade(String student, int subject_id, int grade) {
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(INSERT_GRADE);
			ps.setString(1, student);
			ps.setInt(2, subject_id);
			ps.setInt(3, grade);
			ps.executeUpdate();
		} catch (final SQLException e) {
			System.out.println("SQL exception in adding grade");
			e.printStackTrace();
		}
	}
}

package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public final class StudentSubjectDAO {
	private static final String GET_STUDENT = "SELECT * FROM student_subject where subject_id = ?;";

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
}

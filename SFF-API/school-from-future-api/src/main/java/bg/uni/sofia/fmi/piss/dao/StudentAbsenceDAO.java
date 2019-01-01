package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public final class StudentAbsenceDAO {
	private static final String GET_ABSENCE = "SELECT count FROM student_absence WHERE student_id=? AND subject_id=?;";

	@Autowired
	private DataSource dataSource;

	public int getAbsences(String student, int subjectId) {
		int count = 0;
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(GET_ABSENCE);
			ps.setString(1, student);
			ps.setInt(2, subjectId);
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt("grade");
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting grades");
			e.printStackTrace();
		}
		return count;
	}
}

package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public final class StudentAbsenceDAO {
	private static final String GET_ABSENCE = "SELECT SUM(count) FROM student_absence WHERE student_id=? AND subject_id=?;";
	private static final String EDIT_ABSENCE = "UPDATE student_absence SET count=? WHERE student_id=? AND subject_id=?;";
	private static final String INSERT_ABSENCE = "INSERT INTO student_absence(count, student_id, subject_id) VALUES(?,?,?);";

	@Autowired
	private DataSource dataSource;

	public double getAbsences(String student, int subjectId) {
		double count = 0;
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(GET_ABSENCE);
			ps.setString(1, student);
			ps.setInt(2, subjectId);
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getDouble(1);
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting absences");
			e.printStackTrace();
		}
		return count;
	}

	public void addAbsence(double count, String student, int subjectId) {
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(INSERT_ABSENCE);
			ps.setDouble(1, count);
			ps.setString(2, student);
			ps.setInt(3, subjectId);
			ps.executeUpdate();
		} catch (final SQLException e) {
			System.out.println("SQL exception in setting absences");
			e.printStackTrace();
		}
	}
}

package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public final class StudentGradeDAO {
	private static final String INSERT_GRADE = "INSERT INTO student_grade (student_id, subject_id, grade) VALUES (?,?,?);";
	private static final String GET_GRADES = "SELECT grade FROM student_grade WHERE student_id=? AND subject_id=?;";

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

	public List<Integer> getGrades(String student, int subjectId) {
		final List<Integer> grades = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(GET_GRADES);
			ps.setString(1, student);
			ps.setInt(2, subjectId);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				grades.add(rs.getInt("grade"));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting grades");
			e.printStackTrace();
		}
		return grades;
	}
}

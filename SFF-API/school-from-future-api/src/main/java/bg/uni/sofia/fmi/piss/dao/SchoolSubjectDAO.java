package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import bg.uni.sofia.fmi.piss.dto.Teacher;

public class SchoolSubjectDAO {
	private static final String SELECT_SCHOOLS = "SELECT * FROM school;";
	private static final String SELECT_TEACHERS = "SELECT first_name, last_name, user.email FROM USER JOIN TEACHER ON USER.email = TEACHER.email"
			+ " WHERE user.role='teacher'";
	private static final String INSERT_SUBJECT = "INSERT INTO Subject (name, description, teacher_id) VALUES (?,?,?);";

	@Autowired
	private DataSource dataSource;

	/**
	 * @return list of school names
	 */
	public List<String> getSchools() {
		final List<String> schools = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(SELECT_SCHOOLS);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				schools.add(rs.getString("name"));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting schools");
			e.printStackTrace();
		}
		return schools;
	}

	/**
	 * @return list of all teachers
	 */
	public List<Teacher> getTeachers() {
		final List<Teacher> teachers = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(SELECT_TEACHERS);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				teachers.add(new Teacher(rs.getString("email"), rs.getString("first_name"), rs.getString("last_name")));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting schools");
			e.printStackTrace();
		}
		return teachers;
	}

	/**
	 * @param name
	 *            subject name
	 * @param description
	 *            subject description
	 * @param teacher_id
	 *            teacher email
	 */
	public void insertSubject(String name, String description, String teacher_id) {
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(INSERT_SUBJECT);
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setString(3, teacher_id);
			ps.executeUpdate();
		} catch (final SQLException e) {
			System.out.println("SQL exception in inserting subject");
			e.printStackTrace();
		}
	}
}

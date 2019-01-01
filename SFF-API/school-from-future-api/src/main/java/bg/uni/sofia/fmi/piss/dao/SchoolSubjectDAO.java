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
import bg.uni.sofia.fmi.piss.dto.Teacher;

public class SchoolSubjectDAO {
	private static final String SELECT_TEACHERS = "SELECT first_name, last_name, user.email FROM USER JOIN TEACHER ON USER.email = TEACHER.email"
			+ " WHERE user.role='teacher'";
	private static final String SELECT_SUBJECTS = "SELECT id, name, description, first_name, last_name FROM subject JOIN user ON subject.teacher_id=user.email;";
	private static final String INSERT_SUBJECT = "INSERT INTO Subject (name, description, teacher_id) VALUES (?,?,?);";
	private static final String SELECT_SUBJECTS_BY_TEACHER = "SELECT * FROM subject where teacher_id=?;";
	private static final String DELETE_SUBJECT = "DELETE FROM subject WHERE id=?;";

	@Autowired
	private DataSource dataSource;

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

	/**
	 * @return list of all teacher's subjects
	 */
	public List<SchoolSubject> getTeacherSubjects(String teacher) {
		final List<SchoolSubject> subjects = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(SELECT_SUBJECTS_BY_TEACHER);
			ps.setString(1, teacher);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				subjects.add(new SchoolSubject(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getString("teacher_id")));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting student subjects");
			e.printStackTrace();
		}
		return subjects;
	}

	/**
	 * @return list of all subjects
	 */
	public List<SchoolSubject> getSubjects() {
		final List<SchoolSubject> subjects = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(SELECT_SUBJECTS);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				final String teacherName = rs.getString("first_name").concat(" ").concat(rs.getString("last_name"));
				subjects.add(new SchoolSubject(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						teacherName));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting subjects");
			e.printStackTrace();
		}
		return subjects;
	}

	/**
	 * @param subjectId
	 *            subject to be deleted
	 */
	public void deleteSubject(int subjectId) {
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(DELETE_SUBJECT);
			ps.setInt(1, subjectId);
			ps.executeUpdate();
		} catch (final SQLException e) {
			System.out.println("SQL exception in deleting subject");
			e.printStackTrace();
		}
	}
}

package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import bg.uni.sofia.fmi.piss.data.RegisterData;
import bg.uni.sofia.fmi.piss.dto.User;

public class UserDAO {
	private static final String FIND_USER = "SELECT * FROM USER WHERE email=? AND pass=?";
	private static final String FIND_USER_DATA = "SELECT * FROM USER WHERE email=?";
	private static final String INSERT_USER = "INSERT INTO user(email, first_name, last_name, pass, role) VALUES(?,?,?,?,?);";
	private static final String INSERT_STUDENT = "INSERT INTO student(email, class, class_number) VALUES(?,?,?);";
	private static final String INSERT_ADMIN = "INSERT INTO admin(email) VALUES(?);";
	private static final String INSERT_TEACHER = "INSERT INTO teacher(email) VALUES(?);";

	@Autowired
	private DataSource dataSource;

	/**
	 * @param email
	 *            user's email
	 * @param pass
	 *            user's password
	 * @return
	 */
	public User findUser(String email, String pass) {
		User user = null;
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(FIND_USER);
			ps.setString(1, email);
			ps.setString(2, pass);
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), "",
						rs.getString("role"));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting role");
			e.printStackTrace();
		}
		return user;
	}

	public User getUserData(String email) {
		User user = null;
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(FIND_USER_DATA);
			ps.setString(1, email);
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), "",
						rs.getString("role"));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting role");
			e.printStackTrace();
		}
		return user;
	}

	public void registerUser(RegisterData data) {
		insertUser(data.getEmail(), data.getFirstName(), data.getLastName(), data.getPass(), data.getRole());
		switch (data.getRole()) {
		case "student":
			insertStudent(data.getEmail(), data.getGrade(), data.getGradeNumber());
			break;
		case "teacher":
			insertTeacher(data.getEmail());
			break;
		case "admin":
			insertAdmin(data.getEmail());
			break;
		}
	}

	private void insertUser(String email, String firstName, String lastName, String pass, String role) {
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(INSERT_USER);
			ps.setString(1, email);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, pass);
			ps.setString(5, role);
			ps.executeUpdate();
		} catch (final SQLException e) {
			System.out.println("SQL exception in inserting user");
			e.printStackTrace();
		}
	}

	private void insertTeacher(String email) {
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(INSERT_TEACHER);
			ps.setString(1, email);
			ps.executeUpdate();
		} catch (final SQLException e) {
			System.out.println("SQL exception in inserting user");
			e.printStackTrace();
		}
	}

	private void insertStudent(String email, int grade, int gradeNumber) {
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(INSERT_STUDENT);
			ps.setString(1, email);
			ps.setInt(2, grade);
			ps.setInt(3, gradeNumber);
			ps.executeUpdate();
		} catch (final SQLException e) {
			System.out.println("SQL exception in inserting user");
			e.printStackTrace();
		}
	}

	private void insertAdmin(String email) {
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(INSERT_ADMIN);
			ps.setString(1, email);
			ps.executeUpdate();
		} catch (final SQLException e) {
			System.out.println("SQL exception in inserting user");
			e.printStackTrace();
		}
	}
}

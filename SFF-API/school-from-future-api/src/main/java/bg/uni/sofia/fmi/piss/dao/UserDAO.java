package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import bg.uni.sofia.fmi.piss.data.RegisterData;
import bg.uni.sofia.fmi.piss.data.UserData;
import bg.uni.sofia.fmi.piss.dto.Student;
import bg.uni.sofia.fmi.piss.dto.User;

public class UserDAO {
	private static final String ALL_USERS = "SELECT first_name, last_name, email, role FROM USER;";
	private static final String FIND_USER = "SELECT * FROM USER WHERE email=? AND pass=?";
	private static final String FIND_USER_DATA = "SELECT * FROM USER WHERE email=?";
	private static final String FIND_USER_NAMES = "SELECT first_name, last_name FROM USER WHERE email=?";
	private static final String FIND_STUDENT_DATA = "SELECT * FROM USER JOIN STUDENT ON USER.email = STUDENT.email WHERE user.email=?";
	private static final String INSERT_USER = "INSERT INTO user(email, first_name, last_name, pass, role) VALUES(?,?,?,?,?);";
	private static final String INSERT_STUDENT = "INSERT INTO student(email, class, class_number) VALUES(?,?,?);";
	private static final String INSERT_ADMIN = "INSERT INTO admin(email) VALUES(?);";
	private static final String INSERT_TEACHER = "INSERT INTO teacher(email) VALUES(?);";
	private static final String DELETE_USER = "DELETE FROM user WHERE email = ?";
	private static final String UPDATE_ROLE = "UPDATE user SET first_name=?, last_name=?, email=?, role=? WHERE email=?";

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
			System.out.println("SQL exception in getting user");
			e.printStackTrace();
		}
		return user;
	}

	public List<User> getUsers() {
		final List<User> users = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(ALL_USERS);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				users.add(new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), "",
						rs.getString("role")));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting users");
			e.printStackTrace();
		}
		return users;
	}

	public List<UserData> getChatUsers() {
		final List<UserData> users = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(ALL_USERS);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				users.add(new UserData(rs.getString("first_name").concat(" ").concat(rs.getString("last_name")),
						rs.getString("email")));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting users");
			e.printStackTrace();
		}
		return users;
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

	public void deleteUser(String email) {
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(DELETE_USER);
			ps.setString(1, email);
			ps.executeUpdate();
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting deleting user");
			e.printStackTrace();
		}
	}

	public void updateUser(String prevEmail, String firstName, String lastName, String email, String role) {
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(UPDATE_ROLE);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, email);
			ps.setString(4, role);
			ps.setString(5, prevEmail);
			ps.executeUpdate();
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting updating role");
			e.printStackTrace();
		}
	}

	public Student getStudentData(String email) {
		Student student = null;
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(FIND_STUDENT_DATA);
			ps.setString(1, email);
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				student = new Student(rs.getString("user.first_name"), rs.getString("user.last_name"),
						rs.getString("user.email"), "", rs.getString("user.role"), rs.getInt("student.class"),
						rs.getInt("student.class_number"));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting student data");
			e.printStackTrace();
		}
		return student;
	}

	public String getNameOfUser(String email) {
		String name = null;
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(FIND_USER_NAMES);
			ps.setString(1, email);
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("first_name").concat(" ").concat(rs.getString("last_name"));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting user's names");
			e.printStackTrace();
		}
		return name;
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

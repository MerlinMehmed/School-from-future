package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import bg.uni.sofia.fmi.piss.dto.User;

public class UserDAO {
	private static final String FIND_USER = "SELECT * FROM USER WHERE email=? AND pass=?";

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

}

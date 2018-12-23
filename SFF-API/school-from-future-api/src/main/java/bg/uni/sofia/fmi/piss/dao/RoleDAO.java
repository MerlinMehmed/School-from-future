package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class RoleDAO {
	private static final String SELECT_ALL = "SELECT * FROM ROLE;";

	@Autowired
	private DataSource dataSource;

	RoleDAO() {

	}

	public void getRoles() {
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("NAME"));
				System.out.println(rs.getString("DESCRIPTION"));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting role");
			e.printStackTrace();
		}
	}
}

package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class EventDAO {

	private static final String CREATE_EVENT = "INSERT INTO event(subject_id, date, latitude, longitude) VALUES(?,?,?,?);";

	@Autowired
	private DataSource dataSource;

	public EventDAO() {
	}

	public void addEvent(int subjectId, long date, double latitude, double longitude) {
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(CREATE_EVENT);
			ps.setInt(1, subjectId);
			final Timestamp time = new Timestamp(date);
			ps.setTimestamp(2, time);
			ps.setDouble(3, latitude);
			ps.setDouble(4, longitude);

			ps.executeUpdate();
		} catch (final SQLException e) {
			System.out.println("SQL exception in adding event");
			e.printStackTrace();
		}
	}
}

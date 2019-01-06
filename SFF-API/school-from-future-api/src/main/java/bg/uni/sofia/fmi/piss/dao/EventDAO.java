package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import bg.uni.sofia.fmi.piss.dto.Event;

public class EventDAO {

	private static final String CREATE_EVENT = "INSERT INTO event(subject_id, date, latitude, longitude) VALUES(?,?,?,?);";
	private static final String GET_EVENTS = "SELECT * FROM event WHERE subject_id = ?";

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

	public List<Event> getEvents(int subjectId) {
		final List<Event> events = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(GET_EVENTS);
			ps.setInt(1, subjectId);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				events.add(new Event(rs.getInt("subject_id"), rs.getTimestamp("date").getTime(),
						rs.getDouble("latitude"), rs.getDouble("longitude")));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting events");
			e.printStackTrace();
		}
		return events;
	}
}

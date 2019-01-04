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

import bg.uni.sofia.fmi.piss.dto.Message;

public class MessagesDAO {

	private static final String GET_MESSAGES = "SELECT * FROM Messages WHERE from_email=? AND to_email=?";
	private static final String INSERT_MESSAGE = "INSERT INTO Messages(from_email, to_email, content, date) VALUES(?,?,?,?)";

	@Autowired
	private DataSource dataSource;

	public MessagesDAO() {
	}

	public List<Message> getMessages(String p1, String p2) {
		final List<Message> messages = new ArrayList<>();
		messages.addAll(getMsgsOneSide(p1, p2));
		messages.addAll(getMsgsOneSide(p2, p1));
		return messages;
	}

	public void insertMessage(String from, String to, String content, Timestamp date) {
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(INSERT_MESSAGE);
			ps.setString(1, from);
			ps.setString(2, to);
			ps.setString(3, content);
			ps.setTimestamp(4, date);
			ps.executeUpdate();
		} catch (final SQLException e) {
			System.out.println("SQL exception in inserting message");
			e.printStackTrace();
		}
	}

	private List<Message> getMsgsOneSide(String from, String to) {
		final List<Message> msgs = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(GET_MESSAGES);
			ps.setString(1, from);
			ps.setString(2, to);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				msgs.add(new Message(rs.getString("from_email"), rs.getString("to_email"), rs.getString("content"),
						rs.getTimestamp("date")));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting messages");
			e.printStackTrace();
		}
		return msgs;
	}
}

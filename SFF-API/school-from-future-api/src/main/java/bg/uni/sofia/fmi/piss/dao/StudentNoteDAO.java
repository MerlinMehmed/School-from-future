package bg.uni.sofia.fmi.piss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public final class StudentNoteDAO {
	private static final String GET_NOTES = "SELECT description FROM student_note WHERE student_id=? AND subject_id=?;";
	private static final String ADD_NOTE = "INSERT INTO student_note(student_id, subject_id, description) VALUES(?,?,?)";

	@Autowired
	private DataSource dataSource;

	public List<String> getNotes(String student, int subjectId) {
		final List<String> notes = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(GET_NOTES);
			ps.setString(1, student);
			ps.setInt(2, subjectId);
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				notes.add(rs.getString("description"));
			}
		} catch (final SQLException e) {
			System.out.println("SQL exception in getting notes");
			e.printStackTrace();
		}
		return notes;
	}

	public void addNote(String description, String student, int subjectId) {

		try (Connection conn = dataSource.getConnection()) {
			final PreparedStatement ps = conn.prepareStatement(ADD_NOTE);
			ps.setString(1, student);
			ps.setInt(2, subjectId);
			ps.setString(3, description);
			ps.executeUpdate();
		} catch (final SQLException e) {
			System.out.println("SQL exception in setting absences");
			e.printStackTrace();
		}
	}
}

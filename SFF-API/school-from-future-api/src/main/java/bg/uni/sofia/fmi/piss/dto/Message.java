package bg.uni.sofia.fmi.piss.dto;

import java.sql.Timestamp;

public class Message {

	private String from;
	private String to;
	private String content;
	private Timestamp date;

	public Message() {
	}

	public Message(String from, String to, String content, Timestamp date) {
		super();
		this.from = from;
		this.to = to;
		this.content = content;
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}

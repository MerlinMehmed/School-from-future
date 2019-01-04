package bg.uni.sofia.fmi.piss.data;

import java.io.Serializable;

public class MessageData implements Serializable {

	private String from;
	private String to;

	public MessageData() {
		super();
	}

	public MessageData(String from, String to) {
		super();
		this.from = from;
		this.to = to;
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

}

package bg.uni.sofia.fmi.piss.dto;

public final class Event {

	private int subject;
	private long time;
	private double latitude;
	private double longitude;

	public Event() {
		// TODO Auto-generated constructor stub
	}

	public Event(int subject, long time, double latitude, double longitude) {
		super();
		this.subject = subject;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}

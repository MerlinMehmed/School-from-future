package bg.uni.sofia.fmi.piss.dto;

import java.io.Serializable;

public class Teacher implements Serializable {
	private String email;
	private String firstName;
	private String lastName;

	Teacher() {
		super();
	}

	public Teacher(String email, String first_name, String last_name) {
		super();
		this.email = email;
		firstName = first_name;
		lastName = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_name) {
		firstName = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last_name) {
		lastName = last_name;
	}

}

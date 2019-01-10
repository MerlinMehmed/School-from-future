package bg.uni.sofia.fmi.piss.dto;

public class UserUpdate {

	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String prevEmail;

	public UserUpdate() {
		// TODO Auto-generated constructor stub
	}

	public UserUpdate(String firstName, String lastName, String email, String role, String prevEmail) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.prevEmail = prevEmail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPrevEmail() {
		return prevEmail;
	}

	public void setPrevEmail(String prevEmail) {
		this.prevEmail = prevEmail;
	}

}

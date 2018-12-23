package bg.uni.sofia.fmi.piss.data;

public final class LoginData {
	private String email;
	private String pass;

	public LoginData() {

	}

	public LoginData(String email, String pass) {
		super();
		this.email = email;
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}

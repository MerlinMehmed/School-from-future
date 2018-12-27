package bg.uni.sofia.fmi.piss.data;

public final class RegisterData {
	private String firstName;
	private String lastName;
	private String email;
	private String pass;
	private String role;
	private int grade;
	private int gradeNumber;

	public RegisterData() {

	}

	public RegisterData(String firstName, String lastName, String email, String pass, String role, int grade,
			int gradeNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.role = role;
		this.grade = grade;
		this.gradeNumber = gradeNumber;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getGradeNumber() {
		return gradeNumber;
	}

	public void setGradeNumber(int gradeNumber) {
		this.gradeNumber = gradeNumber;
	}
}

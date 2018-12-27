package bg.uni.sofia.fmi.piss.dto;

public final class Student extends User {

	private int grade;
	private int gradeNumber;

	public Student() {
	}

	public Student(String firstName, String lastName, String email, String pass, String role, int grade,
			int gradeNumber) {
		super(firstName, lastName, email, pass, role);
		this.grade = grade;
		this.gradeNumber = gradeNumber;
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

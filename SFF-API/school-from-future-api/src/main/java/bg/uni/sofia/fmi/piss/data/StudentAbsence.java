package bg.uni.sofia.fmi.piss.data;

public class StudentAbsence {

	private String student;
	private int subjectId;
	private double absence;

	public StudentAbsence() {
	}

	public StudentAbsence(String student, int subjectId, int absence) {
		super();
		this.student = student;
		this.subjectId = subjectId;
		this.absence = absence;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public double getAbsence() {
		return absence;
	}

	public void setAbsence(double absence) {
		this.absence = absence;
	}

}

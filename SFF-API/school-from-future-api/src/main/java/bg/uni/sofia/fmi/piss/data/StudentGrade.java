package bg.uni.sofia.fmi.piss.data;

import java.io.Serializable;

public final class StudentGrade implements Serializable {

	private String student;
	private int subjectId;
	private int grade;

	public StudentGrade() {
	}

	public StudentGrade(String student, int subjectId, int grade) {
		super();
		this.student = student;
		this.subjectId = subjectId;
		this.grade = grade;
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

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}

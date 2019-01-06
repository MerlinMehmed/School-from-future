package bg.uni.sofia.fmi.piss.data;

public class StudentNote {

	private String student;
	private int subjectId;
	private String description;

	public StudentNote() {
	}

	public StudentNote(String student, int subjectId, String description) {
		super();
		this.student = student;
		this.subjectId = subjectId;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

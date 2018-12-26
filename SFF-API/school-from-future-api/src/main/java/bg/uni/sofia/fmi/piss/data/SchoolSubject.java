package bg.uni.sofia.fmi.piss.data;

import java.io.Serializable;

public class SchoolSubject implements Serializable {
	private String name;
	private String description;
	private String schoolName;
	private String teacher;

	public SchoolSubject() {
		super();
	}

	public SchoolSubject(String name, String description, String schoolName, String teacherName) {
		super();
		this.name = name;
		this.description = description;
		this.schoolName = schoolName;
		teacher = teacherName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacherName) {
		teacher = teacherName;
	}

}

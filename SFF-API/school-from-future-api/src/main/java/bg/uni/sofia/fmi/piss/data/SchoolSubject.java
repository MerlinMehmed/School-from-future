package bg.uni.sofia.fmi.piss.data;

import java.io.Serializable;

public class SchoolSubject implements Serializable {
	private int id;
	private String name;
	private String description;
	private String teacher;

	public SchoolSubject() {
		super();
	}

	public SchoolSubject(int id, String name, String description, String teacherName) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		teacher = teacherName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacherName) {
		teacher = teacherName;
	}

}

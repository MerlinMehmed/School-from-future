export class RegisterData {
	firstName: string;
	lastName: string;
	email: string;
	pass: string;
	role: string;
	grade: string;
	gradeNumber: string;

    constructor(firstName: string, lastName: string, email: string, pass: string, role: string, grade: string, gradeNumber: string){
        this.firstName = firstName;
        this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.role = role;
		this.grade = grade;
		this.gradeNumber = gradeNumber;
    }
}

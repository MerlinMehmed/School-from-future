import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GetStudentDataService } from 'src/app/services/get-student-data/get-student-data.service';

@Component({
  selector: 'app-student-profile',
  templateUrl: './student-profile.component.html',
  styleUrls: ['./student-profile.component.css']
})
export class StudentProfileComponent implements OnInit {
	
  subjects: string[];
  notes: string [];
  absences: number;
  grades: number[];
  subject: string;
  student: any;
  studentName: string;
  studentEmail: string;
  studentGrade: number;
  studentGradeNumber: number;
  
  constructor(
    private getStudentDataService: GetStudentDataService,
	private router: Router
  ) { }

  ngOnInit() {
	this.getStudentSubjects();
	this.getStudentAbsences();
	this.getStudentGrades();
	this.getStudentNotes();
  }
  
  getStudentName() {
     this.studentName = JSON.parse(sessionStorage.getItem('user')).firstName + " " + JSON.parse(sessionStorage.getItem('user')).lastName;
  }
  
  getStudentEmail() {
     this.studentEmail = JSON.parse(sessionStorage.getItem('user')).email;
  }
  
  getStudentGrade() {
     this.studentGrade = JSON.parse(sessionStorage.getItem('user')).grade;
  }
  
  getStudentGradeNumber() {
     this.studentGradeNumber = JSON.parse(sessionStorage.getItem('user')).gradeNumber;
  }  
  
  getStudentSubjects() {
    this.student = JSON.parse(sessionStorage.getItem('user')).email;
    let t = this.student.split("@");
    let name = t[0];
    let email = t[1].replace(/\./g, ",");
    this.getStudentDataService.findStudentSubjects(name, email).subscribe(result => {
      console.log(result);
      this.subjects = result;
    });
  }
  
  getStudentGrades() {
    this.student = JSON.parse(sessionStorage.getItem('user')).email;
    let t = this.student.split("@");
    let name = t[0];
    let email = t[1].replace(/\./g, ",");
    this.getStudentDataService.findStudentGrades(name, email, this.subject).subscribe(result => {
      console.log(result);
      this.grades = result;
    });
  }

  getStudentNotes() {
    this.student = JSON.parse(sessionStorage.getItem('user')).email;
    let t = this.student.split("@");
    let name = t[0];
    let email = t[1].replace(/\./g, ",");
    this.getStudentDataService.findStudentNotes(name, email, this.subject).subscribe(result => {
      console.log(result);
      this.notes = result;
    });
  }
  
  getStudentAbsences() {
    this.student = JSON.parse(sessionStorage.getItem('user')).email;
    let t = this.student.split("@");
    let name = t[0];
    let email = t[1].replace(/\./g, ",");
    this.getStudentDataService.findStudentAbsences(name, email, this.subject).subscribe(result => {
      console.log(result);
      this.absences = result;
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddStudentDataService } from 'src/app/services/add-student-data/add-student-data.service';
import { SchoolSubjectService } from 'src/app/services/school-subject/school-subject.service';

@Component({
  selector: 'app-add-grade',
  templateUrl: './add-grade.component.html',
  styleUrls: ['./add-grade.component.css']
})
export class AddGradeComponent implements OnInit {

  subjects: string[];
  students: any[];

  subject: number;
  student: any;

  grade: number;
  absence: number;
  note: string;

  teacher: string;

  fGrade: boolean;
  fAbsence: boolean;
  fNote: boolean;

  constructor(
    private router: Router,
    private studentService: AddStudentDataService,
    private subjectService: SchoolSubjectService
  ) { }

  ngOnInit() {
    this.getSubjects();
  }

  add() {
    console.log(this.student);
    console.log(this.subject);
    console.log(this.grade);
    if (this.fGrade)
      this.studentService.addGrade(this.student, this.subject, this.grade).subscribe(_ => console.log("ok"));
    if (this.fAbsence)
      this.studentService.addAbsence(this.student, this.subject, this.absence).subscribe(_ => console.log("ok"));
    if (this.fNote)
      this.studentService.addNote(this.student, this.subject, this.note).subscribe(_ => console.log("ok"));
  }

  routeToIndex() {
    this.router.navigate(['/index']);
  }

  getSubjects() {
    this.teacher = JSON.parse(sessionStorage.getItem('user')).email;
    // console.log(this.teacher);
    let t = this.teacher.split("@");
    let name = t[0];
    let email = t[1].replace(/\./g, ",");
    this.subjectService.findSubjects(name, email).subscribe(result => {
      console.log(result);
      this.subjects = result;
    });
  }

  getStudents() {
    this.studentService.getStudents(this.subject).subscribe(result => {
      console.log(result);
      this.students = result
    });
  }
}

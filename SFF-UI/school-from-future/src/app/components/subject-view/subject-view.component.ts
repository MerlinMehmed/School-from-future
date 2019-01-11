import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SchoolSubjectService } from 'src/app/services/school-subject/school-subject.service';
import { AddStudentDataService } from 'src/app/services/add-student-data/add-student-data.service';
import { GetStudentDataService } from 'src/app/services/get-student-data/get-student-data.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-subject-view',
  templateUrl: './subject-view.component.html',
  styleUrls: ['./subject-view.component.css']
})
export class SubjectViewComponent implements OnInit {

  subjects: string[];
  students: any[];
  grades: any[][] = [];
  name: string;
  email: string;
  subject: number;

  teacher: string;

  constructor(
    private router: Router,
    private subjectService: SchoolSubjectService,
    private studentService: AddStudentDataService,
    private getStudentDataService: GetStudentDataService,
    private modalService: NgbModal
  ) { }

  ngOnInit() {
    this.getSubjects();
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
      this.students = result;
      for (var i = 0; i < this.students.length; i++) {
        this.getStudentGrades(this.students[i].email, this.subject, i);
      }
    });
  }

  getStudentGrades(student: string, subject, i: number) {
    var name = student.split("@")[0];
    var email = student.split("@")[1].replace(/\./g, ",");
    this.getStudentDataService.findStudentGrades(name, email, subject).subscribe(result => {
      console.log(result);
      this.grades[student] = result;
    });
  }

  open(content) {
    // this.modalService.open(content, { centered: true });
  }
}

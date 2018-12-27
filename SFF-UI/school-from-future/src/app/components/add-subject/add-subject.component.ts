import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SchoolSubjectService } from 'src/app/services/school-subject/school-subject.service';
import { Route } from '@angular/compiler/src/core';

@Component({
  selector: 'app-add-subject',
  templateUrl: './add-subject.component.html',
  styleUrls: ['./add-subject.component.css']
})
export class AddSubjectComponent implements OnInit {

  name: string;
  description: string;
  teacherName: string;
  schoolName: string;
  // teacher: string;

  schools: string[];
  teachers: any[];

  constructor(
    private subjectService: SchoolSubjectService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getTeachers();
  }

  add() {
    this.subjectService.addSubject(this.name, this.description, this.teacherName, this.schoolName).subscribe(
      _ => console.log("OK")
    );
  }

  routeToIndex() {
    this.router.navigate(['/index']);
  }

  getTeachers() {
    console.log(this.schoolName);
    this.subjectService.getTeacher(this.schoolName).subscribe(result => {
      console.log(result);
      this.teachers = result;
    });
  }
}

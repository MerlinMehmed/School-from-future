import { Component, OnInit } from '@angular/core';
import { SchoolSubjectService } from 'src/app/services/school-subject/school-subject.service';

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
    private subjectService: SchoolSubjectService
  ) { }

  ngOnInit() {
    this.getTeachers();
  }

  add() {
    this.subjectService.addSubject(this.name, this.description, this.teacherName, this.schoolName).subscribe(
      _=>console.log("OK")
    );
  }

  routeToIndex() {

  }

  getTeachers() {
    console.log(this.schoolName);
    this.subjectService.getTeacher(this.schoolName).subscribe(result=>
      {
        console.log(result);
        this.teachers=result;
      });
  }
}

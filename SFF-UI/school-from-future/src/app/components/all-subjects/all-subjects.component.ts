import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SchoolSubjectService } from 'src/app/services/school-subject/school-subject.service';

@Component({
  selector: 'app-all-subjects',
  templateUrl: './all-subjects.component.html',
  styleUrls: ['./all-subjects.component.css']
})
export class AllSubjectsComponent implements OnInit {
	
  subjects: string[];
  subjectName: string;
  teacherName: string;
  description: string;

  constructor(
    private router: Router,
    private subjectService: SchoolSubjectService
  ) { }

  ngOnInit() {
	this.getAllSubjects();
  }
  
  getAllSubjects() {
    this.subjectService.findAllSubjects().subscribe(result => {
      console.log(result);
      this.subjects = result;
    });
  }
  
  getSubjectTeacher() {
	this.subjectService.getSubjectTeacher(this.subjectName).subscribe(result => {
      console.log(result);
      this.teacherName = result; 
	});
  }
  
    getSubjectDescription() {
	this.subjectService.getSubjectDescription(this.subjectName).subscribe(result => {
      console.log(result);
      this.description = result; 
	});
  }
  
  deleteSubject() {
	this.subjectService.deleteSubject(this.subjectName).subscribe();
  }
  
  routeToIndex() {
    this.router.navigate(['/index']);
  }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SchoolSubjectService } from 'src/app/services/school-subject/school-subject.service';
import { Subject } from 'src/app/model/Subject';

@Component({
  selector: 'app-all-subjects',
  templateUrl: './all-subjects.component.html',
  styleUrls: ['./all-subjects.component.css']
})
export class AllSubjectsComponent implements OnInit {

  subjects: Subject[];

  constructor(
    private router: Router,
    private subjectService: SchoolSubjectService
  ) { }

  ngOnInit() {
    this.getAllSubjects();
  }

  getAllSubjects() {
    this.subjectService.findAllSubjects().subscribe(result => {
      this.subjects = result;
    });
  }

  deleteSubject(subject: number) {
    this.subjectService.deleteSubject(subject).subscribe(() => {
      this.getAllSubjects();
    });
  }

  routeToIndex() {
    this.router.navigate(['/index']);
  }

}

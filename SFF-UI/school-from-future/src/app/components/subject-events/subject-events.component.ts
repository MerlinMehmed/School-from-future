import { Component, OnInit } from '@angular/core';
import { SchoolSubjectService } from 'src/app/services/school-subject/school-subject.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EventService } from 'src/app/services/event/event.service';
import { SubjectEvent } from 'src/app/model/SubjectEvent';
import { Time } from '@angular/common';
import { useAnimation } from '@angular/animations';
import { User } from 'src/app/model/user';
import { GetStudentDataService } from 'src/app/services/get-student-data/get-student-data.service';

@Component({
  selector: 'app-subject-events',
  templateUrl: './subject-events.component.html',
  styleUrls: ['./subject-events.component.css']
})
export class SubjectEventsComponent implements OnInit {

  subjects: any[];
  events: SubjectEvent[];
  // teacher: string;
  subject: number;
  lat: number = 42.698334;
  lng: number = 23.319941;


  constructor(
    private subjectService: SchoolSubjectService,
    private getStudentDataService: GetStudentDataService,
    private eventService: EventService,
    private modalService: NgbModal

  ) { }

  ngOnInit() {
    this.getSubjects();
  }

  getSubjects() {
    var user = JSON.parse(sessionStorage.getItem('user'));
    var t = user.email.split("@");
    let name = t[0];
    let email = t[1].replace(/\./g, ",");

    if (user.role == 'teacher')
      this.subjectService.findSubjects(name, email).subscribe(result => {
        console.log(result);
        this.subjects = result;
      });
    else if (user.role == 'student')
      this.getStudentDataService.findStudentSubjects(name, email).subscribe(result => {
        console.log(result);
        this.subjects = result;
      });
    else if (user.role == 'admin')
    this.subjectService.findAllSubjects().subscribe(result => {
      this.subjects = result;
    });
  }

  getSubjectEvents() {
    this.events = [];
    this.eventService.getSubjectEvents(this.subject).subscribe(res => {
      for (let event of res) {
        let datetime = new Date(event.time);
        this.events.push(new SubjectEvent(event.subject, datetime.toLocaleDateString(), datetime.toLocaleTimeString(), event.latitude, event.longitude));
      }
    })
  }

  open(content, lat, lng) {
    this.lat = lat;
    this.lng = lng;
    console.log(this.lat + " " + this.lng)
    this.modalService.open(content, { centered: true });
  }
}

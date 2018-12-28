import { Component, OnInit } from '@angular/core';
import { EventService } from 'src/app/services/event/event.service';
import { SchoolSubjectService } from 'src/app/services/school-subject/school-subject.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {
  subjects: string[];
  subject: number;

  date: Date = new Date();
  eventDate: Date;
  eventTime: any = "09:00";

  lat: number = 42.698334;
  lng: number = 23.319941;
  locationChosen = false;

  constructor(
    private subjectService: SchoolSubjectService,
    private eventService: EventService
  ) { }

  ngOnInit() {
    this.eventDate = new Date(Date.now());
    console.log(this.eventDate);
    this.getSubjects();
  }

  addEvent() {
    let date = new Date(this.eventDate);
    date.setHours(this.eventTime.split(":")[0]);
    date.setMinutes(this.eventTime.split(":")[1]);
    this.eventService.addEvent(this.subject, date.getTime(), this.lat, this.lng).subscribe();
  }

  changeLocation(event) {
    this.lat = event.coords.lat;
    this.lng = event.coords.lng;
    this.locationChosen = true;
  }

  getSubjects() {
    let teacher = JSON.parse(sessionStorage.getItem('user')).email;
    // console.log(this.teacher);
    let t = teacher.split("@");
    let name = t[0];
    let email = t[1].replace(/\./g, ",");
    this.subjectService.findSubjects(name, email).subscribe(result => {
      this.subjects = result;
    });
  }
}

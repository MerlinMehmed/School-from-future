import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private addEventUrl = environment.url + '/add-event';
  private getEventsUrl = environment.url + '/events';
  
  constructor(private http: HttpClient) { }

  addEvent(subject: number, date: number, lat: number, lng: number): Observable<any> {
    var data = {
      'subject': subject,
      'time': date,
      'latitude': lat,
      'longitude': lng
    }
    console.log(data);
    return this.http.post(this.addEventUrl, data);
  }

  getSubjectEvents(subject: number): Observable<any> {
    let url = this.getEventsUrl + "/"+subject;
    return this.http.get(url);
  }
}

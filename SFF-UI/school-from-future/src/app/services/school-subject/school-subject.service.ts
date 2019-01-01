import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { headersToString } from 'selenium-webdriver/http';
import { Subject } from 'src/app/model/Subject';

@Injectable({
  providedIn: 'root'
})
export class SchoolSubjectService {

  private schoolsUrl = environment.url + '/schools';
  private teacherUrl = environment.url + '/teachers';
  private insertUrl = environment.url + '/add-subject';
  private subjectsUrl = environment.url + '/get-subjects';
  private allSubjectsUrl = environment.url + '/all-subjects';
  private removeSubjectUrl = environment.url + '/delete-subject';

  constructor(private http: HttpClient) { }

  getTeacher(school: string): Observable<string[]> {
    var url = this.teacherUrl;
    console.log(url);
    return this.http.get<string[]>(url);
  }

  getSubjectTeacher(subject: string): Observable<string> {
    var url = this.teacherUrl;
    console.log(url);
    return this.http.get<string>(url);
  }

  getSubjectDescription(subject: string): Observable<string> {
    var url = this.subjectsUrl + "/" + subject.toString();
    console.log(url);
    return this.http.get<string>(url);
  }

  addSubject(name: string, descirption: string, teacher: string, school: string) {
    var data = {
      'name': name,
      'description': descirption,
      'schoolName': school,
      'teacher': teacher
    }
    console.log(data);
    return this.http.post(this.insertUrl, data);
  }

  findSubjects(teacher: string, email: string): Observable<any[]> {
    var url = this.subjectsUrl + "/" + teacher.toString() + "/" + email;
    console.log(url);
    return this.http.get<any[]>(url);
  }

  findAllSubjects(): Observable<Subject[]> {
    console.log(this.allSubjectsUrl);
    return this.http.get<any[]>(this.allSubjectsUrl);
  }

  deleteSubject(subject: number): Observable<any> {
    return this.http.put(this.removeSubjectUrl, subject);
  }
}

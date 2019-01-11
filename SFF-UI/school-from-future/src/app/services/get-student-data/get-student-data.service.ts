import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from 'src/app/model/Student';

@Injectable({
  providedIn: 'root'
})
export class GetStudentDataService {

  private studentUrl = environment.url + '/student';
  private subjectsUrl = environment.url + '/subjects';
  private gradesUrl = environment.url + '/grades';
  private absencesUrl = environment.url + '/absences';
  private notesUrl = environment.url + '/notes';


  constructor(private http: HttpClient) { }

  findGradeData(student: string, email: string): Observable<Student> {
    var url = this.studentUrl + "/" + student.toString() + "/" + email;
    console.log(url);
    return this.http.get<Student>(url);
  }

  findStudentSubjects(student: string, email: string): Observable<any[]> {
    var url = this.subjectsUrl + "/" + student.toString() + "/" + email;
    console.log(url);
    return this.http.get<any[]>(url);
  }

  findStudentGrades(student: string, email: string, subject: number): Observable<any[]> {
    var url = this.gradesUrl + "/" + student.toString() + "/" + email + "/" + subject;
    console.log(url);
    return this.http.get<any[]>(url);
  }

  findStudentNotes(student: string, email: string, subject: number): Observable<any[]> {
    var url = this.notesUrl + "/" + student.toString() + "/" + email + "/" + subject;
    console.log(url);
    return this.http.get<any[]>(url);
  }

  findStudentAbsences(student: string, email: string, subject: number): Observable<number> {
    var url = this.absencesUrl + "/" + student.toString() + "/" + email + "/" + subject;
    console.log(url);
    return this.http.get<number>(url);
  }
}

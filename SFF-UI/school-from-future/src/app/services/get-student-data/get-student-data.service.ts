import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { headersToString } from 'selenium-webdriver/http';

@Injectable({
  providedIn: 'root'
})
export class GetStudentDataService {
	
  private studentUrl = environment.url + '/students';
  private subjectsUrl = environment.url + '/get-subjects';

  constructor(private http: HttpClient) { }
  
  findStudentSubjects(student: string, email:string): Observable<any[]> {
    var url = this.subjectsUrl+"/"+student.toString()+"/"+email;
    console.log(url);
    return this.http.get<any[]>(url);
  }
  
  findStudentGrades(student: string, email:string, subject: string): Observable<any[]> {
    var url = subject+"/"+student.toString()+"/"+email;
    console.log(url);
    return this.http.get<any[]>(url);
  }
  
  findStudentNotes(student: string, email:string, subject: string): Observable<any[]> {
    var url = subject+"/"+student.toString()+"/"+email;
    console.log(url);
    return this.http.get<any[]>(url);
  }
  
  findStudentAbsences(student: string, email:string, subject: string): Observable<number> {
    var url = subject+"/"+student.toString()+"/"+email;
    console.log(url);
    return this.http.get<number>(url);
  }
}

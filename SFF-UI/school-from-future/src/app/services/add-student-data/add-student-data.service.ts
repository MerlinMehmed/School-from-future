import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddStudentDataService {

  private subjectsUrl = environment.url + '/get-students';
  private addGradeUrl = environment.url + '/add-grade';


  constructor(private http: HttpClient) { }

  getStudents(subject: number): Observable<any[]> {
    var url = this.subjectsUrl + "/" + subject;
    return this.http.get<any[]>(url);
  }

  addGrade(student: string, subject: number, grade: number) :Observable<any> {
    var data = {
        'student': student,
        'subjectId': subject,
        'grade': grade
    }
    console.log(data);
    return this.http.post<any>(this.addGradeUrl, data);
  }
}

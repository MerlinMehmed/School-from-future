import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { headersToString } from 'selenium-webdriver/http';
import { UserData } from 'src/app/model/UserData';

@Injectable({
  providedIn: 'root'
})

export class UserDataService {

  private allUsersUrl = environment.url + '/users';
  private removeUserUrl = environment.url + '/delete-user';
  private updateUserUrl = environment.url + '/update-user';

  constructor(private http: HttpClient) { }

  findAllUsers(): Observable<UserData[]> {
    console.log(this.allUsersUrl);
    return this.http.get<any[]>(this.allUsersUrl);
  }

  deleteUser(email: string): Observable<any> {
    return this.http.post(this.removeUserUrl, email);
  }

  updateUser(prevEmail: string, firstName: string, lastName: string, email: string, role:string): Observable<any> {
    var data = {
      prevEmail: prevEmail,
      firstName: firstName,
      lastName: lastName,
      email: email,
      role: role
    }
    return this.http.post(this.updateUserUrl, data);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { LoginData } from 'src/app/model/LoginData';
import { environment } from 'src/environments/environment';
import { LoginUser } from 'src/app/model/LoginUser';
import { User } from 'src/app/model/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = environment.url + '/login';
  private logoutUrl = environment.url + '/logout';
  private chatUsersUrl = environment.url + '/chat-users';

  constructor(private http: HttpClient) { }

  login(loginData: LoginData): Observable<LoginUser> {
    return this.http.post<LoginUser>(this.loginUrl, loginData);
  }

  getLoginUser(): LoginUser {
    return JSON.parse(sessionStorage.getItem('user'));
  }

  logout() : Observable<any> {
    return this.http.post(this.logoutUrl, this.getLoginUser());
  }

  getChatUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.chatUsersUrl);
  }

}

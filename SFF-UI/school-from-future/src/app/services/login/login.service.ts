import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { LoginData } from 'src/app/model/LoginData';
import { environment } from 'src/environments/environment';
import { LoginUser } from 'src/app/model/LoginUser';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = environment.url + '/login';

  constructor(private http: HttpClient) { }

  login(loginData: LoginData): Observable<LoginUser> {
    return this.http.post<LoginUser>(this.loginUrl, loginData);
  }

  getLoginUser(): LoginData {
    return JSON.parse(sessionStorage.getItem('user'));
  }

}

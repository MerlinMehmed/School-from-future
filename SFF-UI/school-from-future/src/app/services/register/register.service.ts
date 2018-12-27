import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { RegisterData } from 'src/app/model/RegisterData';
import { environment } from 'src/environments/environment';
import { LoginUser } from 'src/app/model/LoginUser';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private registerUrl = environment.url + '/register';

  constructor(private http: HttpClient) { }

  register(registerData: RegisterData): Observable<LoginUser> {
    return this.http.post<LoginUser>(this.registerUrl, registerData);
  }

  getRegisterUser(): RegisterData {
    return JSON.parse(sessionStorage.getItem('user'));
  }

}

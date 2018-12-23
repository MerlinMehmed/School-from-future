import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginData } from 'src/app/model/LoginData';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;

  loginUser: LoginData;

  constructor(
    private loginService: LoginService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  login() {
    return this.loginService.login(this.makeLoginData()).subscribe(loginUser => {
      if (loginUser) {
        console.log(loginUser);
        sessionStorage.setItem('user', JSON.stringify(loginUser));
        this.router.navigate(['/index']);
      }
    });
  }

  makeLoginData(): LoginData {
    return {
      email: this.username,
      pass: this.password
    }
  }

  public routeToIndex() {
    this.router.navigate(['/index']);
  }

}

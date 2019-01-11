import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginData } from 'src/app/model/LoginData';
import { LoginService } from 'src/app/services/login/login.service';
import { SocketService } from 'src/app/services/socket/socket.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;

  loginUser: LoginData;
  success = true;
  constructor(
    private loginService: LoginService,
    private socketService: SocketService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  login() {
    return this.loginService.login(this.makeLoginData()).subscribe(loginUser => {
      if (loginUser) {
        console.log(loginUser);
        sessionStorage.setItem('user', JSON.stringify(loginUser));
        // this.socketService.initSocket(new User(loginUser.firstName.concat(" ").concat(loginUser.lastName), loginUser.email));
        this.router.navigate(['/index']);
      } else {
        this.success = false;
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

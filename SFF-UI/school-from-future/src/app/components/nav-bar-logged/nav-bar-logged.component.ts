import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUser } from 'src/app/model/LoginUser';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-nav-bar-logged',
  templateUrl: './nav-bar-logged.component.html',
  styleUrls: ['./nav-bar-logged.component.css']
})
export class NavBarLoggedComponent implements OnInit {
  user: LoginUser;

  constructor(
    private loginService: LoginService,
    private router: Router
  ) { }

  ngOnInit() {
    this.user = this.loginService.getLoginUser();
    console.log(this.user);
  }

  logout() {
    this.loginService.logout();
    sessionStorage.removeItem('user');
    this.user = null;
    this.router.navigate(['/index']);
  }

}

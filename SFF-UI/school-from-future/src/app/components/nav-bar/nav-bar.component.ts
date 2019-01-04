import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login/login.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(
    private loginService: LoginService
  ) { }

  ngOnInit() {
  }
  
  isLogged() {
    return sessionStorage.getItem('user')!==null;
  }
  
  isTeacher() {
	var currentUser = JSON.parse(sessionStorage.getItem('user'));
    return currentUser['role']=="teacher";
  }
  
  isAdmin() {
	var currentUser = JSON.parse(sessionStorage.getItem('user'));
    return currentUser['role']=="admin";
  }
  
  isStudent() {
	var currentUser = JSON.parse(sessionStorage.getItem('user'));
    return currentUser['role']=="student";
  }

  logout() {
    this.loginService.logout();
  }

}

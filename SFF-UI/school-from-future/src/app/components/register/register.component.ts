import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterData } from 'src/app/model/RegisterData';
import { RegisterService } from 'src/app/services/register/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  first: string;
  last: string;
  username: string;
  password: string;
  userRole: string;
  studentGrade: string;
  gradeNumber: string;

  success = false;
  student = false;

  registerUser: RegisterData;

  constructor(
    private registerService: RegisterService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  checkRole() {
    if (this.userRole == 'student')
      this.student = true;
    else
      this.student = false; 
  }

  register() {
    return this.registerService.register(this.makeRegisterData()).subscribe(registerUser => {
      this.success = true;
      if (registerUser) {
        console.log(registerUser);
        // sessionStorage.setItem('user', JSON.stringify(registerUser));
        // this.router.navigate(['/login']);
      }
    });
  }

  makeRegisterData(): RegisterData {
    return {
      firstName: this.first,
      lastName: this.last,
      email: this.username,
      pass: this.password,
      role: this.userRole,
      grade: this.studentGrade,
      gradeNumber: this.gradeNumber
    }
  }

  public routeToIndex() {
    this.router.navigate(['/index']);
  }
}

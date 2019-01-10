import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDataService } from 'src/app/services/user-data/user-data.service';
import { UserData } from 'src/app/model/UserData';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {

  users: UserData[];
  currentEmail: string;
  newEmail: string;
  newFirstName: string;
  newLastName: string;
  newRole: string;

  constructor(
    private router: Router,
    private userData: UserDataService,
    private modalService: NgbModal

  ) { }

  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers() {
    this.userData.findAllUsers().subscribe(result => {
      this.users = result;
    });
  }

  deleteUser(email: string) {
    this.userData.deleteUser(email).subscribe(() => {
      this.getAllUsers();
    });
  }

  updateUser() {
    this.userData.updateUser(this.currentEmail, this.newFirstName, this.newLastName, this.newEmail, this.newRole).subscribe(() => {
      this.getAllUsers();
    });
  }

  open(content, u: UserData) {
    this.currentEmail = u.email;
    this.newEmail = this.currentEmail;
    this.newFirstName = u.firstName;
    this.newLastName = u.lastName;
    this.newRole = u.role;

    this.modalService.open(content, { centered: true });
  }
}

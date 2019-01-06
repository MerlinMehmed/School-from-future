import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDataService } from 'src/app/services/user-data/user-data.service';
import { UserData } from 'src/app/model/UserData';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {
	
	 users: UserData[];

  constructor(
	private router: Router,
    private userData: UserDataService
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
}

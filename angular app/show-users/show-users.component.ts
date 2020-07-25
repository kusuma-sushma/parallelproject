import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../library.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-users',
  templateUrl: './show-users.component.html',
  styleUrls: ['./show-users.component.css']
})

export class ShowUsersComponent implements OnInit {
  users;

  constructor(private libraryService: LibraryService, private router: Router) {
    this.getUserDetails();
  }

  getUserDetails() {
    return this.libraryService.getAllUsers().subscribe(response => {
      console.log(response);
      this.users = response.usersList;
    }, error => {
      console.log(error);
    })
  }

  ngOnInit(): void {
  
  }

}

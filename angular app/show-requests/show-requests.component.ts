import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../library.service';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';


@Component({
  selector: 'app-show-requests',
  templateUrl: './show-requests.component.html',
  styleUrls: ['./show-requests.component.css']
})
export class ShowRequestsComponent implements OnInit {
  issue;
  requests;
  message;
  book;
  error;
  isAdminLogin :boolean;
  constructor(private libraryService : LibraryService, private authService : AuthService,
    private router:Router) { 
      this.getRequests();
    }

  ngOnInit(): void {
  }

  getRequests(){
    return this.libraryService.getRequest().subscribe(response => {
      console.log(response);
      this.requests = response.usersRequestList;
    }, error => {
      console.log(error);
    })
  }

  issueBook(requests) {
    this.libraryService.postIssue(requests).subscribe(response => {
      console.log(response);
      if (response.error) {
        this.message = 'Book has already Issued';
      } else {
        this.getRequests();
        this.message = 'Book has Issued Successfully';
      }
      setTimeout(() => {
        this.message = null;
      }, 5000);
    });


  }
   }



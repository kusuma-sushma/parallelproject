import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { LibraryService } from '../library.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  error;
  message;
  isLoggedIn : boolean;
  isNotLoggedIn: boolean;
  books;
  searchText;
fieldName = "bookName";

  constructor( private router : Router, public authService:AuthService,private libraryService: LibraryService) {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.isNotLoggedIn = this.authService.isNotLoggedIn();
    }
    
    getBooks() {
      return this.libraryService.getBooks().subscribe(response => {
        console.log(response);
        this.books = response.booksList;
      }, error => {
        console.log(error);
      })
    }
    

  
  
  ngOnInit(): void {
  }

  logoutClick(){
    localStorage.clear();
  }

}

import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../library.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-booklist',
  templateUrl: './booklist.component.html',
  styleUrls: ['./booklist.component.css']
})
export class BooklistComponent implements OnInit {
error;
message: String;
books;
isAdminLogin: boolean;
isUserLogin: boolean;
request: RequestInfo;
searchText;
fieldName = "bookName";

constructor(private libraryService: LibraryService, private authService: AuthService, private router: Router) {
  this.getBooks();
  this.isAdminLogin = authService.isAdmin();
  this.isUserLogin = authService.isUser();
}

getBooks() {
  return this.libraryService.getBooks().subscribe(response => {
    console.log(response);
    this.books = response.booksList;
  }, error => {
    console.log(error);
  })
}
deleteBook(book) {
  this.libraryService.deleteBook(book).subscribe(response => {
    console.log(response);
    if (response.error) {
      this.message =  "Unable to delete Book";
    } else {
      this.getBooks();
      this.message = "Book Deleted Successfully";
    }
    setTimeout(() => {
      this.message = null;
    }, 5000);
  });

}

updateBook(book) {
  this.router.navigate(['/updateBook'], { queryParams: book });
  
}
postReq(request) {
  console.log(request.bookId);

  this.authService.postBorrow(request).subscribe(response => {

    console.log(response);
    if (!response.error) {
      this.message = response.message;
      setTimeout(() => {
        this.message = null;
      }, 5000);
    } else {
      this.message = response.message;
      setTimeout(() => {
        this.message = null;
      }, 5000);
    }
  }, error => {
    console.log(error);
  });
}
postReturnBook(books) {
  this.authService.postReturn(books).subscribe(response => {
    console.log(response);
    if (response.error) {
      this.message =response.message;
      setTimeout(() => {
        this.message = null;
      }, 5000);
    } else {
      this.message ="Book Returned Successfully";
      setTimeout(() => {
        this.message = null;
      }, 5000);
    }
  }, error => {
    console.log(error);
  });
}

ngOnInit(): void {
}

}
class RequestInfo {
userId: String;
bookId: String;
}

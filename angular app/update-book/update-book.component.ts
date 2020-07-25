import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../library.service';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {

    error;
    message: String;
    updateBook;
    bookAvaliable: boolean;
    bookId: Number;
    bookAuthor: String;
    bookName : String;
    bookPublisher : String;
    bookCategory : String;
    
    constructor(private activatedRoute: ActivatedRoute, private libraryService: LibraryService ) {
      this.activatedRoute.queryParams.subscribe(data => {
        console.log('Book which needs to be Updated', data);
        this.updateBook = data;
        this.bookAvaliable = data.bookAvaliable;
  
      });
    }
  
  
    ngOnInit(): void {
      this.bookId = this.updateBook.bookId;
      this.bookAvaliable = this.updateBook.bookAvaliable;
      this.bookAuthor = this.updateBook.bookAuthor;
      this.bookName = this.updateBook.bookName;
      this.bookPublisher = this.updateBook.bookPublisher;
      this.bookCategory = this.updateBook.bookCategory;
    }
  
  
    postUpdate(form: NgForm){
      this.libraryService.postUpdate(form.value).subscribe(response => {
        console.log(response); 
        
        if (response.error) {
          this.error = response.message;
          setTimeout(() => {
            this.error = null;
          }, 5000);
        } else {
          this.message= response.message;
          setTimeout(() => {
            this.message = null; 
           },5000);
          }
          response.navigateByUrl('/allBooks');
        });
      }
    }
    
  
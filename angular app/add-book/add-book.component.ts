import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LibraryService } from '../library.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
error;
  message:string;
  constructor(private libraryService : LibraryService) { }
  ngOnInit(): void {
  
  }

  
  postaddBook(form: NgForm){
   return this.libraryService.postAddBook(form.value).subscribe(response =>{
      console.log(response);
      form.reset();
      if(response.error){
        this.message="Book already exists unable to add the book";
        setTimeout(() => {
         this.error = null; 
        },5000);
      }else{
        this.message='Book added successfully';
        setTimeout(() => {
          this.message = null; 
         },5000);
      } error => {
        console.log(error);
      }
    });
  }
}




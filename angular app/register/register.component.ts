import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../library.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
userId;
  message:string;
  error:string;
  constructor(private libraryService : LibraryService) { }

  ngOnInit(): void {
  }

  registerUser(form: NgForm){
    return this.libraryService.postData(form.value).subscribe(response =>{
      console.log(response);
    
      if(response.error){
        this.error="User Email Id already exists";
        setTimeout(() => {
         this.error = null; 
        },5000);
      } else {
        this.message='User Added successfully';
        setTimeout(() => {
          this.message = null; 
         },5000);
      } error => {
        console.log(error);
      }
      form.reset();
    });
  }
}


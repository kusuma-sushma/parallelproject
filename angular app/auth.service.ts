import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  
  lmsUrl='http://localhost:8080/librarymanagementsystemspringrest/';
  constructor(private http: HttpClient) { }


  login(userDetails){
    return this.http.post<any>(`${this.lmsUrl}adminLogin`,userDetails);
  }

  postBorrow(book){
    console.log(book,this.getUserId());
    return this.http.post<any>(`${this.lmsUrl}borrowBook/${this.getUserId()}`,book);
  }
  postReturn(book) {
    return this.http.post<any>(`${this.lmsUrl}returnBook/${this.getUserId()}`,book);
  }

  getUserId(){
    var user=JSON.parse(localStorage.getItem('userDetails'));

    return user.userId;
  }




  isAdmin(){
    var role =  localStorage.getItem('role');
    if(role == 'admin'){
    return true;
    }
    else{
    return false;
    }
  }
  isUser(){
    var role =  localStorage.getItem('role');
    if(role == 'student'){
    return true;
    }
    else{
    return false;
    }
  }
  
  isLoggedIn(){
    var role = localStorage.getItem('role');
    if(role == 'student' || role == 'admin'){
      return true;
    }else{
      return false;
    }
  }
  isNotLoggedIn(){
    var role = localStorage.getItem('role');
    if(role != 'student' && role != 'admin'){
      return true;
    }else{
      return false;
    }
  }

}

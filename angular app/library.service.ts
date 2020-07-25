import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LibraryService {
  lmsUrl="http://localhost:8080/librarymanagementsystemspringrest/"
  id;
  
  constructor(private http:HttpClient){}

  postData(user){
    return this.http.post<any>(`${this.lmsUrl}addUser`,user);
  }
  postAddBook(book){
    return this.http.post<any>(`${this.lmsUrl}addBook`,book);
  }
  searchBook(book){
      return this.http.get<any>(`http://localhost:8080/librarymanagementsystemspringrest/getBook/${book.bookId}`);
  }

  getBooks(){
    return this.http.get<any>(`${this.lmsUrl}getAllBooks`);
  }
  
  postIssue(request) {
    return this.http.post<any>(`${this.lmsUrl}issueBook`,request);
  }
  deleteBook(book) {
    return this.http.delete<any>(`http://localhost:8080/librarymanagementsystemspringrest/deleteBook/${book.bookId}`);
  }
  postUpdate(book) {
    return this.http.put<any>(`${this.lmsUrl}updateBook`, book);
  }
  getRequest() {
    return this.http.get<any>(`${this.lmsUrl}getAllRequests`);
  }
  getAllUsers() {
    return this.http.get<any>(`${this.lmsUrl}getAllUsers`);
  }
  
}

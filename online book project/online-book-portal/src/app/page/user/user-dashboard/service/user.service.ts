import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Author, Book } from '../../../../model/class';
import { User } from '../../../../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  //  return this.http.delete<any>(`${this.deleteBookUrl}/${id}`);
 
private apiUrl = 'http://localhost:8082/api/admin/books/withAuthor';
private getAuthorsUrl = 'http://localhost:8082/api/user/get/all/authors';
private userUrl = 'http://localhost:8082/api/user/get/all/user';
private deleteUserUrl = 'http://localhost:8082/api/user/delete/user';
private updateUserUrl = 'http://localhost:8082/api/user/update/user';

  constructor(private http: HttpClient) {}

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.apiUrl);
  }
  getAllAuthors(): Observable<Author[]> {
    return this.http.get<any>(this.getAuthorsUrl);
  }
  getUser(): Observable<any> {
    return this.http.get<any>(this.userUrl);
  }
   deleteUser(id: number): Observable<User> {
      // return this.http.delete<any>(this.deleteAuthorUrl);
      //  return this.http.delete<any>(`${this.deleteBookUrl}/${id}`);
        return this.http.delete<any>(`${this.deleteUserUrl}/${id}`);
   }
         updateUser(user: User) : Observable<User> {
     return this.http.put<User>(this.updateUserUrl, user);
  
  
//  author: AddAuthor): Observable<Author> {
//    return this.http.put<Author>(this.editAuthorUrl, author);
//  }
  }
}

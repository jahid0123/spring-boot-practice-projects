import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Author, Book } from '../../../../model/class';
import { User } from '../../../../model/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  //  return this.http.delete<any>(`${this.deleteBookUrl}/${id}`);

  private apiUrl = 'http://localhost:8082/api/user/get/all/books';
  private getAuthorsUrl = 'http://localhost:8082/api/user/get/all/authors';
  private userUrl = 'http://localhost:8082/api/admin/get/all/users';
  private deleteUserUrl = 'http://localhost:8082/api/user/delete/userbyid';
  private updateUserUrl = 'http://localhost:8082/api/user/update/userbyid';

  // user property
  private editUserInfoApiUrl = 'http://localhost:8082/api/user/edit/my/info';
  private getUserInfoApiUrl = 'http://localhost:8082/api/user/get/my/info';
  private getMyAllOrderApiUrl ='http://localhost:8082/api/user/get/my/all/order';
  private deleteMyOrderApiUrl = 'http://localhost:8082/api/user/delete/my/orderbyid';

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
  updateUser(user: User): Observable<User> {
    return this.http.put<User>(this.updateUserUrl, user);

    //  author: AddAuthor): Observable<Author> {
    //    return this.http.put<Author>(this.editAuthorUrl, author);
    //  }
  }

  editUserInfo(data: any): Observable<any> {
    const id = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('id', id.toString());
    return this.http.put<any>(this.editUserInfoApiUrl, data, { params });
  }

  getUserInfo(): Observable<any> {
    const id = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('id', id.toString());
    return this.http.get<any>(this.getUserInfoApiUrl, { params });
  }

  getMyAllOrder(): Observable<any> {
    const id = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('id', id.toString());
    return this.http.get<any>(this.getMyAllOrderApiUrl, { params });
  }

  deleteMyOrder(id: number): Observable<User> {
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete<any>(this.deleteMyOrderApiUrl, {params});
  }
}

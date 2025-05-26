import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddBookService {

  private addBookUrl = 'http://localhost:8082/api/admin/add/book';
  private deleteBookUrl = 'http://localhost:8082/api/admin/delete/book';
  private editBookUrl = 'http://localhost:8082/api/admin/update/book';
  private getAllBookUrl = 'http://localhost:8082/api/user/get/all/books';

  constructor(private http: HttpClient) { }


  addBook(data: any): Observable<any>{
    return this.http.post<any>(this.addBookUrl, data);
  }

  getAllBooks(): Observable<any>{
    return this.http.get<any>(this.getAllBookUrl);
  }

  editBook(data: any): Observable<any>{
    return this.http.put<any>(this.editBookUrl, data);
  }

deleteBook(id: number): Observable<any> {
  return this.http.delete<any>(`${this.editBookUrl}/${id}`);
}


}

import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddAuthor, Author } from '../../../../model/class';

@Injectable({
  providedIn: 'root',
})
export class AddAuthorService {
  private addAuthorUrl = 'http://localhost:8082/api/admin/add/author';
  private deleteAuthorUrl = 'http://localhost:8082/api/admin/delete/author';
  private editAuthorUrl = 'http://localhost:8082/api/admin/update/author';
  private getAllAuthorUrl = 'http://localhost:8082/api/admin/get/all/authors';

  constructor(private http: HttpClient) {}

  addAuthor(data: any): Observable<any> {
    return this.http.post<any>(this.addAuthorUrl, data);
  }

  getAllAuthors(): Observable<any> {
    return this.http.get<any>(this.getAllAuthorUrl);
  }
  editAuthor(author: AddAuthor): Observable<Author> {
    return this.http.put<Author>(this.editAuthorUrl, author);
  }

  deleteAuthor(id: number): Observable<any> {
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete<any>(this.deleteAuthorUrl, { params });
  }
}

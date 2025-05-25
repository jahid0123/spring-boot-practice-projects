import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../../../../model/class';

@Injectable({
  providedIn: 'root'
})
export class UserService {
private apiUrl = 'http://localhost:8082/api/user/get/all/books';

  constructor(private http: HttpClient) {}

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.apiUrl);
  }
}

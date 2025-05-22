import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

   private apiUrl = 'http://localhost:8082/api/customer/get/all/package';

  constructor(private http: HttpClient) {}

  getAllPackages(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}

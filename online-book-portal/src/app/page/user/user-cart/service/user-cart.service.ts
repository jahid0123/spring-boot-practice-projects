import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserCartService {

   private apiUrl = 'http://localhost:8082/api/user/order/request';

  constructor(private http: HttpClient) {}

  placeOrder(orderData: { userId: number; bookIds: number[] }): Observable<any> {
    return this.http.post<any>(this.apiUrl, orderData);
  }
}

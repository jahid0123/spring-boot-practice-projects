import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserCartService {

   private apiUrl = 'http://localhost:8082/api/user/order/request';
   private userInformationUrl = 'http://localhost:8082/api/user/get/my/info';

  constructor(private http: HttpClient) {}

  placeOrder(orderData: { userId: number; bookIds: number[] }): Observable<any> {
    return this.http.post<any>(this.apiUrl, orderData);
  }

  profileInfo():Observable<any>{
    const id = Number(localStorage.getItem('id'))
    const params = new HttpParams().set('id', id.toString());
    return this.http.get<any>(this.userInformationUrl, {params});
  }
}

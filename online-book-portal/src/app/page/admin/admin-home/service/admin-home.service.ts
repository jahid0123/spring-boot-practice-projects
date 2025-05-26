import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderResponse } from '../../../../model/class';

@Injectable({
  providedIn: 'root'
})
export class AdminHomeService {

    private apiUrl = 'http://localhost:8082/api/admin/get/all/order';

  constructor(private http: HttpClient) {}

  getAllOrders(): Observable<OrderResponse[]> {
    return this.http.get<OrderResponse[]>(this.apiUrl);
  }
}

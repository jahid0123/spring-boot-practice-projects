import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderResponse } from '../../../../model/class';

@Injectable({
  providedIn: 'root',
})
export class AdminHomeService {
  private apiUrl = 'http://localhost:8082/api/admin/get/all/order';
  private deleteOrderbyIdUrl = 'http://localhost:8082/api/admin/delete/order';
  private orderDeliveredApiUrl =
    'http://localhost:8082/api/admin/order/delivered';
  private orderCompletedApiUrl =
    'http://localhost:8082/api/admin/order/completed';
  private orderCancelledApiUrl =
    'http://localhost:8082/api/admin/order/cancelled';

  constructor(private http: HttpClient) {}

  getAllOrders(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  deleteOrder(orderId: number): Observable<void> {
    const params = new HttpParams().set('id', orderId.toString());
    return this.http.delete<void>(this.deleteOrderbyIdUrl, { params });
  }

  orderDelivered(id: number): Observable<any> {
    const params = new HttpParams().set('orderId', id.toString());
    return this.http.put<any>(this.orderDeliveredApiUrl, null, { params });
  }

  orderCompleted(id: number): Observable<any> {
    const params = new HttpParams().set('orderId', id.toString());
    return this.http.put<any>(this.orderCompletedApiUrl, null, { params });
  }

  orderCancelled(id: number): Observable<any> {
    const params = new HttpParams().set('orderId', id.toString());
    return this.http.put<any>(this.orderCancelledApiUrl, null, { params });
  }
}

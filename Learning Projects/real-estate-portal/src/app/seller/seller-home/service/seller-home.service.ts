import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../../../auth/service/auth.service';

@Injectable({
  providedIn: 'root',
})
export class SellerHomeService {

  private getPropertyBySellerApiURL =
    'http://localhost:8081/api/seller/property/posted/me';
  private deletePropertyBySellerApiURL =
    'http://localhost:8081/api/seller/posted/property/delete';
  private editPropertyBySellerApiURL =
    'http://localhost:8081/api/seller/posted/property/update';

  constructor(private http: HttpClient, private router: Router) {}

  getAllPost(): Observable<any> {
    const sellerId = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('id', sellerId.toString());
    return this.http.get<any>(this.getPropertyBySellerApiURL, {params});
  }

  updatePost(data: any): Observable<any> {
    return this.http.put<any>(this.editPropertyBySellerApiURL, data);
  }

  deletePost(id: number): Observable<any> {
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete<any>(this.deletePropertyBySellerApiURL, {params});
  }
}

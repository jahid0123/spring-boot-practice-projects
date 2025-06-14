import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BuyerRequestService {

  private getMyPropertyRequestApiURL =
    'http://localhost:8081/api/buyer/property/info/request/by/me';


  constructor(private http: HttpClient, private router: Router) {}

  getAllPost(): Observable<any> {
    const buyerId = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('id', buyerId.toString());
    return this.http.get<any>(this.getMyPropertyRequestApiURL, {params});
  }


}

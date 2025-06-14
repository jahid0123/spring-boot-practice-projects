import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BuyerHomeService {
  private getAllPostedPropertyApiURL =
    'http://localhost:8081/api/buyer/all/posted/property';

  private requestPropertyInfoApiURL =
    'http://localhost:8081/api/buyer/property/info/request';

  constructor(private http: HttpClient, private router: Router) {}

  getAllPost(): Observable<any> {
    return this.http.get<any>(this.getAllPostedPropertyApiURL);
  }

  propertyInfoRequest(data: {userId: number, propertyPostId: number}): Observable<any> {
    return this.http.post<any>(this.requestPropertyInfoApiURL, data);
  }

  // deletePost(id: number): Observable<any> {
  //   const params = new HttpParams().set('id', id.toString());
  //   return this.http.delete<any>(this.deletePropertyBySellerApiURL, {params});
  // }
}

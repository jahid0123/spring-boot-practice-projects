import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddDoctoerService {

  private addDoctorApiUrl = "http://localhost:8081/api/admin/doctor/register"

  constructor(private http: HttpClient) { }

  addDoctor(data: any): Observable<any>{
   return this.http.post<any>(this.addDoctorApiUrl, data);
  } 
}

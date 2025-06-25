import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admission } from './indoor.service';

@Injectable({
  providedIn: 'root'
})
export class AdmissionService {

  private baseUrl = 'http://localhost:8080/api/admission';

  constructor(private http: HttpClient) {}

  getAllAdmissions(): Observable<Admission[]> {
    return this.http.get<Admission[]>(`${this.baseUrl}/all`);
  }
}

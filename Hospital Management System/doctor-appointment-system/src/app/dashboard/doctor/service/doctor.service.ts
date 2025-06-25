import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private http: HttpClient) { }

  getAdmittedPatientsByDoctor(doctorId: number): Observable<any> {
  return this.http.get<any>(`http://localhost:8081/api/doctor/${doctorId}/patients`);
}

}

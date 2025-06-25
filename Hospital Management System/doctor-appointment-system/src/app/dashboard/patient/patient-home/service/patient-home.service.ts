import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DoctorListDto } from '../../../../model/model.classes';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PatientHomeService {

  private getAllApiUrl = 'http://localhost:8081/api/patient/get/doctors';

  constructor(private http: HttpClient) {}

  getAllDoctors(): Observable<any> {
    return this.http.get<any>(this.getAllApiUrl);
  }
}

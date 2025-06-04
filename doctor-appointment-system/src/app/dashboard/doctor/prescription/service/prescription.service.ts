import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GetAppointmentsPatient, PrescriptionRequest } from '../../../../model/model.classes';

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {

 private createPrescriptionApiUrl = 'http://localhost:8081/api/doctor/create/prescription';
 private getAllAppByMeUrl = 'http://localhost:8081/api/doctor';

  constructor(private http: HttpClient) {}

  createPrescription(prescription: PrescriptionRequest): Observable<any> {
    return this.http.post(this.createPrescriptionApiUrl, prescription);
  }

   // Get all approved appointments for the doctor (you can adjust API if needed)
  getApprovedAppointments(doctorId: number): Observable<GetAppointmentsPatient[]> {
    return this.http.get<GetAppointmentsPatient[]>(`${this.getAllAppByMeUrl}/get/all/app/list/by/me?id=${doctorId}`);
  }
}

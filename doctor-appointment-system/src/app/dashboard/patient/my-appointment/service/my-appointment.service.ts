import { Injectable } from '@angular/core';
import { GetAppointmentsPatient } from '../../../../model/model.classes';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MyAppointmentService {

  private apiUrl = 'http://localhost:8081/api/patient/all/appointmentlist';
  private gerPrescriptionApiUrl = 'http://localhost:8081/api/patient/get/prescription/by/app';

  constructor(private http: HttpClient) {}

  getAppointments(): Observable<GetAppointmentsPatient[]> {
  const patientId = Number(localStorage.getItem('id'));
  const params = { id: patientId.toString() }; // or simply { id: `${id}` }

  return this.http.get<GetAppointmentsPatient[]>(this.apiUrl, { params });
}

getPrecriptionByAppId(appId: number): Observable<any>{

  const params = { id: appId.toString() }; // or simply { id: `${id}` }
  return this.http.get<any>(this.gerPrescriptionApiUrl, {params});
}

}

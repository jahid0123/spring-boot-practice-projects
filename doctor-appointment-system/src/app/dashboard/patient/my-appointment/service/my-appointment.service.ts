import { Injectable } from '@angular/core';
import { GetAppointmentsPatient } from '../../../../model/model.classes';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MyAppointmentService {

  private apiUrl = 'http://localhost:8081/api/patient/all/appointmentlist';

  constructor(private http: HttpClient) {}

  getAppointments(): Observable<GetAppointmentsPatient[]> {
  const patientId = Number(localStorage.getItem('id'));
  const params = { id: patientId.toString() }; // or simply { id: `${id}` }

  return this.http.get<GetAppointmentsPatient[]>(this.apiUrl, { params });
}
}

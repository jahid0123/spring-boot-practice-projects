import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppointmentRequest } from '../../../../model/model.classes';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PatientAppointmentService {

 private bookAppointmentApiUrl = 'http://localhost:8081/api/patient/book/appointment/by/patient';

  constructor(private http: HttpClient) {}

  // Book an appointment
  bookAppointment(appointment: AppointmentRequest): Observable<any> {
    return this.http.post(this.bookAppointmentApiUrl, appointment);
  }

}

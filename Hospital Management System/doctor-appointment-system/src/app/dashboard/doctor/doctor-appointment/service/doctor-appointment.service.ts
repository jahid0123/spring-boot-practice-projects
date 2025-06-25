import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GetAppointmentsPatient } from '../../../../model/model.classes';

@Injectable({
  providedIn: 'root'
})
export class DoctorAppointmentService {

   private getMyAppointmentApiUrl = 'http://localhost:8081/api/doctor/get/all/app/list/by/me';
   private getAllAppointmentApiUrl = 'http://localhost:8081/api/admin/get/all/app/list';
  private updateAppointmentStatusApiUrl = 'http://localhost:8081/api/doctor/update/appointment/status';
   private generatePrescriptionApiUrl = 'http://localhost:8081/api/doctor/prescription/generate';

  constructor(private http: HttpClient) {}


  // 2. Get all appointments by doctor ID
  getAppointmentsByDoctorId(): Observable<GetAppointmentsPatient[]> {
    const doctorId = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('id', doctorId.toString());
    return this.http.get<GetAppointmentsPatient[]>(this.getMyAppointmentApiUrl,{ params });
  }

  getAllAppointments(): Observable<GetAppointmentsPatient[]> {
    return this.http.get<GetAppointmentsPatient[]>(this.getAllAppointmentApiUrl);
  }

  // 3. Update appointment status (Approve or Reject)
  updateStatus(appointmentId: number, status: 'CONFIRMED' | 'REJECTED'): Observable<any> {
    return this.http.put(this.updateAppointmentStatusApiUrl,{ appointmentId, status });
  }

  // 4. Optional: Generate prescription (can be form navigation)
  generatePrescription(appointmentId: number): Observable<any> {
    return this.http.post(this.generatePrescriptionApiUrl, {appointmentId});
  }
}

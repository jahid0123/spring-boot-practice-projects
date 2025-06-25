import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ManageUsersService {

  private baseUrl = 'http://localhost:8081/api/admin';

  private getUsersApiUrl = `${this.baseUrl}/get/all/users`;
  private getPatientsApiUrl = `${this.baseUrl}/get/all/patients`;
  private getDoctorsApiUrl = `${this.baseUrl}/get/all/doctors`;

  constructor(private http: HttpClient) {}

  // 🔹 Get All Users
  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(this.getUsersApiUrl);
  }

  // 🔹 Get All Patients
  getAllPatients(): Observable<any[]> {
    return this.http.get<any[]>(this.getPatientsApiUrl);
  }

  // 🔹 Get All Doctors
  getAllDoctors(): Observable<any[]> {
    return this.http.get<any[]>(this.getDoctorsApiUrl);
  }
}

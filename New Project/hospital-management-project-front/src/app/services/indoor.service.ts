import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


export interface Bed {
  id: number;
  ward: string;
  bedNumber: string;
  occupied: boolean;
}

export interface Admission {
  id: number;
  admissionDate: string;
  dischargeDate?: string;
  patientId: number;
  bedId: number;
  discharged: boolean;
}

export interface Bill {
  id: number;
  roomCharge: number;
  doctorFee: number;
  medicineCost: number;
  total: number;
  admissionId: number;
}

@Injectable({
  providedIn: 'root'
})
export class IndoorService {

 private baseUrl = 'http://localhost:8080/api';  // change if needed

  constructor(private http: HttpClient) { }

  getAvailableBeds(): Observable<Bed[]> {
    return this.http.get<Bed[]>(`${this.baseUrl}/bed/available`);
  }

  admitPatient(patientId: number, bedId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/admission/admit?patientId=${patientId}&bedId=${bedId}`, {});
  }

  dischargePatient(admissionId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/admission/discharge?admissionId=${admissionId}`, {});
  }

  generateBill(admissionId: number, roomCharge: number, doctorFee: number, medicineCost: number): Observable<Bill> {
    return this.http.post<Bill>(`${this.baseUrl}/bill/generate?admissionId=${admissionId}&roomChargePerDay=${roomCharge}&doctorFee=${doctorFee}&medicineCost=${medicineCost}`, {});
  }

  // Add more API calls as needed
}

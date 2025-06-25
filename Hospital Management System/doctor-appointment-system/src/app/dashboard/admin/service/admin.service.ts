import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private baseUrl = 'http://localhost:8081/api/admin';

  private addBedApiUrl = 'http://localhost:8081/api/admin/add/bed';
  private getAllBedApiUrl = 'http://localhost:8081/api/admin/get/all/beds';
  private deleteBedApiUrl = 'http://localhost:8081/api/admin/delete/bed';
  private editBedApiUrl = 'http://localhost:8081/api/admin/edit/bed';
  private admitPatientApiUrl = 'http://localhost:8081/api/admin/admission';

  constructor(private http: HttpClient) {}

  getAllBed(): Observable<any> {
    return this.http.get<any>(this.getAllBedApiUrl);
  }

  addBed(data: any): Observable<any> {
    return this.http.post<any>(this.addBedApiUrl, data);
  }

  editBed(data: any): Observable<any> {
    return this.http.put<any>(this.editBedApiUrl, data);
  }

  deleteBed(id: number): Observable<any> {
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete<any>(this.deleteBedApiUrl, { params });
  }

  admitPatient(data: any): Observable<any> {
    return this.http.post<any>(this.admitPatientApiUrl, data);
  }

  getDashboardStats(): Observable<any> {
    return this.http.get<any>(
      'http://localhost:8081/api/admin/dashboard/stats'
    );
  }

  getAdmittedPatients(): Observable<any[]> {
    return this.http.get<any[]>(
      'http://localhost:8081/api/admin/admitted-patients'
    );
  }

  getAllAdmittedPatients(): Observable<any[]> {
    return this.http.get<any[]>(
      'http://localhost:8081/api/admin/admitted-patients'
    );
  }

  addAdvancePayment(id: number, amount: number): Observable<any> {
    return this.http.put(
      `http://localhost:8081/api/admin/advance?id=${id}&amount=${amount}`,
      {}
    );
  }

  dischargePatient(id: number): Observable<any> {
    return this.http.put(
      `http://localhost:8081/api/admin/discharge?id=${id}`,
      {}
    );
  }

   // Download PDF receipt
  downloadReceipt(admissionId: number): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/download/receipt?id=${admissionId}`, {
      responseType: 'blob',
    });
  }

  // Generate bill for an admission
  generateBill(admissionId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/bill/generate/${admissionId}`, {});
  }

  // Get bill for an admission
  getBill(admissionId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/bill/${admissionId}`);
  }

  // Alternatively, if you use different endpoint for bill by admission
  getBillForAdmission(admissionId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/admissions/bill/${admissionId}`);
  }

  // Create bill with doctor fee and medicine cost
  createBill(
    admissionId: number,
    doctorFee: number,
    medicineCost: number
  ): Observable<any> {
    const url = `${this.baseUrl}/bill/create?admissionId=${admissionId}&doctorFee=${doctorFee}&medicineCost=${medicineCost}`;
    return this.http.post(url, null);
  }

  // Fetch bill by admissionId
  billByAdmissionId(admissionId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/bill/${admissionId}`);
  }

  getDischargedPatients() {
  return this.http.get<any[]>('http://localhost:8081/api/admin/discharged');
}
}

import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private getAllUsersUrlApi = 'http://localhost:8081/api/admin/all/users';
  private getAllBillsUrlApi = 'http://localhost:8081/api/admin/all/bills';
  private getAllSeekersUrlApi = 'http://localhost:8081/api/admin/all/seekers';
  private getAllCompaniesUrlApi = 'http://localhost:8081/api/admin/all/companies';
  private createPackageUrlApi = 'http://localhost:8081/api/admin/create/package';
  private getAllPackageUrlApi = 'http://localhost:8081/api/admin/all/packages';
  private getAllAppliesUrlApi = 'http://localhost:8081/api/admin/all/applies';
  private getAllJobsUrlApi = 'http://localhost:8081/api/admin/all/jobs';
  private getProfileInfoUrlApi = 'http://localhost:8081/api/admin/profile/information';
  private changePasswordUrlApi = 'http://localhost:8081/api/admin/change-password';
  private editUserInfoUrlApi = 'http://localhost:8081/api/admin/edit/user/information';
  private editPackageUrlApi = 'http://localhost:8081/api/admin/edit/package';
  private deleteJobUrlApi = 'http://localhost:8081/api/admin/delete/job';
  private deleteApplicationUrlApi = 'http://localhost:8081/api/admin/delete/application';
  private deleteSeekerUrlApi = 'http://localhost:8081/api/admin/delete/seekerbyid';
  private deleteCompanyUrlApi = 'http://localhost:8081/api/admin/delete/companybyid';
  private deleteAdminUrlApi = 'http://localhost:8081/api/admin/delete/adminbyid';
  private deletePackageUrlApi = 'http://localhost:8081/api/admin/delete/packagebyid';




  constructor(private http: HttpClient) {}

  getAllUsers():Observable<any>{
    return this.http.get<any>(this.getAllUsersUrlApi);
  }
  getAllSeekers():Observable<any>{
    return this.http.get<any>(this.getAllSeekersUrlApi);
  }

  getAllCompanies():Observable<any>{
    return this.http.get<any>(this.getAllCompaniesUrlApi);
  }

  getAllPackage():Observable<any>{
    return this.http.get<any>(this.getAllPackageUrlApi);
  }

  getAllApplies():Observable<any>{
    return this.http.get<any>(this.getAllAppliesUrlApi);
  }

  getAllJobs():Observable<any>{
    return this.http.get<any>(this.getAllJobsUrlApi);
  }

  getAllBills():Observable<any>{
    return this.http.get<any>(this.getAllBillsUrlApi);
  }

  getProfileInfo():Observable<any>{
    const userId = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('userId', userId.toString());
    return this.http.get<any>(this.getProfileInfoUrlApi, {params});
  }

  editUserInfo(data: {userId: number; name: string; phone: string}):Observable<any>{
    return this.http.put<any>(this.editUserInfoUrlApi, data);
  }

  changePassword(data: {id: number; currentPassword: string; newPassword: string}):Observable<any>{
    return this.http.put<any>(this.changePasswordUrlApi, data);
  }

  editPackage(data: {id: number; name: string; jobPostLimit: number; applicantViewLimit: number; validityMonth: number; price: number}):Observable<any>{
    return this.http.put<any>(this.editPackageUrlApi, data);
  }


  createPackage(data: any):Observable<any>{
    return this.http.post<any>(this.createPackageUrlApi, data);
  }

  deletePackage(id: number){
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete(this.deletePackageUrlApi, {params});
  }


  deleteJob(id: number){
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete(this.deleteJobUrlApi, {params});
  }

  deleteApplication(id: number){
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete(this.deleteApplicationUrlApi, {params});
  }

  deleteSeeker(id: number){
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete(this.deleteSeekerUrlApi, {params});
  }

  deleteCompany(id: number){
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete(this.deleteCompanyUrlApi, {params});
  }

  deleteAdmin(id: number){
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete(this.deleteAdminUrlApi, {params});
  }

}

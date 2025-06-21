import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private getMyProfileInformationApi = 'http://localhost:8081/api/company/profile/information';
  private editMyProfileInformationApi = 'http://localhost:8081/api/company/edit/profile/information';
  private changeMyPasswordApi = 'http://localhost:8081/api/company/change-password';
  private createJobUrlApi = 'http://localhost:8081/api/company/job/post';
  private editJobUrlApi = 'http://localhost:8081/api/company/edit/job';
  private getMyAllPostedJobUrlApi = 'http://localhost:8081/api/company/get/my/all/posted/job';
  private getMyPostedJobApplicationUrlApi = 'http://localhost:8081/api/company/get/my/posted/job/applies/by';
  private getMyPostedJobAppliesByJobIdUrlApi = 'http://localhost:8081/api/company/get/my/all/posted/job/apply';
  private getAllPackagesUrlApi = 'http://localhost:8081/api/company/all/packages';
  private purchagePackageUrlApi = 'http://localhost:8081/api/company/purchase/packages';
  private myPurchagePackageUrlApi = 'http://localhost:8081/api/company/my/purchase/packages';
  private createShortListUrlApi = 'http://localhost:8081/api/company/create/shortlist';
  private allShortListByCopanyUrlApi = 'http://localhost:8081/api/company/by-company';
  private allShortListByJobUrlApi = 'http://localhost:8081/api/company/by-job';
  private deleteShortListUrlApi = 'http://localhost:8081/api/company/delete/shortlist';
  private deleteJobByIdUrlApi = 'http://localhost:8081/api/company/delete/jobbyid';
  private getMyAllBillsUrlApi = 'http://localhost:8081/api/company/get/my/bills';




  constructor(private http: HttpClient) {}

  createJob(data: any):Observable<any>{
    const companyId = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('id', companyId.toString());
    return this.http.post<any>(this.createJobUrlApi, data, {params});
  }

  editJob(id: number, data: any):Observable<any>{
  const params = new HttpParams().set('id', id.toString());
    return this.http.put<any>(this.editJobUrlApi, data, {params});
  }

  deleteJobById(id: number){
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete(this.deleteJobByIdUrlApi, {params});
  }

  getProfileInformation():Observable<any>{
    return this.http.get<any>(this.getMyProfileInformationApi);
  }

  getMyPostedJob():Observable<any>{
    const id = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('companyId', id.toString());
    return this.http.get<any>(this.getMyAllPostedJobUrlApi, {params});
  }

  getMyPostedJobApplies():Observable<any>{
    const id = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('companyId', id.toString());
    return this.http.get<any>(this.getMyPostedJobApplicationUrlApi, {params});
  }

  getMyPostedJobAppliesByJobId(id: number):Observable<any>{
    const params = new HttpParams().set('jobId', id.toString());
    return this.http.get<any>(this.getMyPostedJobAppliesByJobIdUrlApi, {params});
  }

  allShortListByJobId(id: number):Observable<any>{
    const params = new HttpParams().set('jobId', id.toString());
    return this.http.get<any>(this.allShortListByJobUrlApi, {params});
  }

  allShortListByCompanyId(id: number):Observable<any>{
    const params = new HttpParams().set('id', id.toString());
    return this.http.get<any>(this.allShortListByCopanyUrlApi, {params});
  }
  createShortlist(data: any):Observable<any>{
    return this.http.post<any>(this.createShortListUrlApi, data);
  }

  getAllPackages():Observable<any>{
    return this.http.get<any>(this.getAllPackagesUrlApi);
  }

  getMyPurchagePackage():Observable<any>{
    const companyId = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('companyId', companyId.toString());
    return this.http.get<any>(this.myPurchagePackageUrlApi, {params});
  }

  purchasePackage(data: any):Observable<any>{
    return this.http.post<any>(this.purchagePackageUrlApi, data);
  }

  getMyProfileInfo():Observable<any>{
    const companyId = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('companyId', companyId.toString());
    return this.http.get<any>(this.getMyProfileInformationApi, {params});
  }

  editCompanyInfo(data: any):Observable<any>{
    return this.http.put<any>(this.editMyProfileInformationApi, data);
  }

  changePassword(data: {id: number; currentPassword: string; newPassword: string}):Observable<any>{
    return this.http.put<any>(this.changeMyPasswordApi, data);
  }

  deleteShortlistById(id: number){
    const params = new HttpParams().set('id', id.toString());
    return this.http.delete(this.deleteShortListUrlApi, {params});
  }

  getAllBills():Observable<any>{
    const companyId = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('companyId', companyId.toString());
    return this.http.get<any>(this.getMyAllBillsUrlApi, {params});
  }


}

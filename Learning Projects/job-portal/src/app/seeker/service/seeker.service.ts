import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SeekerService {

  private getMyProfileInformationApi = 'http://localhost:8081/api/seeker/profile/information';
  private editMyProfileInformationApi = 'http://localhost:8081/api/seeker/edit/profile/information';
  private changeMyPasswordApi = 'http://localhost:8081/api/seeker/change-password';


  private getAllPostedJobUrlApi = 'http://localhost:8081/api/seeker/get/all/posted/job';
  private applyJobUrlApi = 'http://localhost:8081/api/seeker/apply/job';
  private getMyAppliedJobsUrlApi = 'http://localhost:8081/api/seeker/my/applied/jobs';
  private getShortlistMyAppliedJobUrlApi = 'http://localhost:8081/api/seeker/my/shortlisted/applied/job';





  constructor(private http: HttpClient) {}

 

  getProfileInformation():Observable<any>{
    return this.http.get<any>(this.getMyProfileInformationApi);
  }


  getMyProfileInfo():Observable<any>{
    const seekerId = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('id', seekerId.toString());
    return this.http.get<any>(this.getMyProfileInformationApi, {params});
  }

  editProfileInfo(data: any):Observable<any>{
    return this.http.put<any>(this.editMyProfileInformationApi, data);
  }

  changePassword(data: {id: number; currentPassword: string; newPassword: string}):Observable<any>{
    return this.http.put<any>(this.changeMyPasswordApi, data);
  }

  getAllJobs():Observable<any>{
    return this.http.get<any>(this.getAllPostedJobUrlApi);
  }

  applyJob(data: any):Observable<any>{
    return this.http.post<any>(this.applyJobUrlApi, data);
  }

  getMyAppliedJobs():Observable<any>{
    const seekerId = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('id', seekerId.toString());
    return this.http.get<any>(this.getMyAppliedJobsUrlApi, {params});
  }

  getShortlistMyAppliedJob():Observable<any>{
    const seekerId = Number(localStorage.getItem('id'));
    const params = new HttpParams().set('seekerId', seekerId.toString());
    return this.http.get<any>(this.getShortlistMyAppliedJobUrlApi, {params});
  }


}

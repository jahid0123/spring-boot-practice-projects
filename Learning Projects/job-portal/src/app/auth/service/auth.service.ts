import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginApiURL = 'http://localhost:8081/api/auth/login';
   private seekerRegisterApiURL = 'http://localhost:8081/api/auth/seeker/register'; // Corrected register URL
   private companyRegisterApiURL = 'http://localhost:8081/api/auth/company/register'; // Corrected register URL

  constructor(private http: HttpClient, private router: Router) {}

  login(data: { email: string; password: string }): Observable<any> {
    return this.http.post<any>(this.loginApiURL, data).pipe(
      tap((res) => {
        localStorage.setItem('token', res.token);
        localStorage.setItem('role', res.role);
        localStorage.setItem('id', res.id);
      })
    );
  }

  companyRegister(data: any): Observable<any> {
    return this.http.post<any>(this.companyRegisterApiURL, data);
  }

  seekerRegister(data: any): Observable<any> {
    return this.http.post<any>(this.seekerRegisterApiURL, data);
  }

  redirectByRole(role: string) {
    switch (role) {
      case 'COMPANY':
        this.router.navigate(['/company-dashboard']);
        break;
      case 'SEEKER':
        this.router.navigateByUrl('/seeker-dashboard');
        break;
      case 'ADMIN':
        this.router.navigateByUrl('/admin-layout');
        break;
      default:
        this.router.navigateByUrl('/login');
    }
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  getRole() {
    return localStorage.getItem('role');
  }

  getToken() {
    return localStorage.getItem('token');
  }

  getId() {
    return localStorage.getItem('id');
  }
}

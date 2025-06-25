import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class AuthService {

   private loginApiURL = 'http://localhost:8081/api/auth/login';
   private registerApiURL = 'http://localhost:8081/api/auth/patient/register'; // Corrected register URL
   private adminRegisterApiURL = 'http://localhost:8081/api/auth/user/register'; // Corrected register URL

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

  register(data: { name: string; email: string; password: string; gender: string; dob: Date}): Observable<any> {
    return this.http.post<any>(this.registerApiURL, data);
  }

  adminRegister(data: { name: string; email: string; password: string}): Observable<any> {
    return this.http.post<any>(this.registerApiURL, data);
  }

  redirectByRole(role: string) {
    switch (role) {
      case 'PATIENT':
        this.router.navigate(['/patient']);
        break;
      case 'DOCTOR':
        this.router.navigateByUrl('/doctor');
        break;
      case 'ADMIN':
        this.router.navigateByUrl('/admin');
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
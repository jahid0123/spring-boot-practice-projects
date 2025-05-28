import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

   private api = 'http://localhost:8080/auth';
  
  constructor(private http: HttpClient, private router: Router) {}

  login(data: { email: string, password: string }) {
    return this.http.post<{ token: string, role: string }>(`${this.api}/login`, data)
      .pipe(tap(res => {
        localStorage.setItem('token', res.token);
        localStorage.setItem('role', res.role);
        this.redirectByRole(res.role);
      }));
  }

  register(data: { name: string, email: string, password: string, role: string }) {
    return this.http.post<{ token: string, role: string }>(`${this.api}/register`, data)
      .pipe(tap(res => {
        localStorage.setItem('token', res.token);
        localStorage.setItem('role', res.role);
        this.redirectByRole(res.role);
      }));
  }

  private redirectByRole(role: string) {
    switch (role) {
      case 'PATIENT': this.router.navigate(['/patient']); break;
      case 'DOCTOR': this.router.navigate(['/doctor']); break;
      case 'ADMIN': this.router.navigate(['/admin']); break;
    }
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  getToken() {
    return localStorage.getItem('token') || '';
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

 private loginUrl = 'http://localhost:8082/api/auth/login';
 private signupUrl = 'http://localhost:8082/api/auth/register';

  private roleSubject = new BehaviorSubject<string | null>(this.getUserRole());
  public role$ = this.roleSubject.asObservable();

  constructor(private http: HttpClient) {}

  register(userData: {name: string; email: string; password: string}): Observable<any> {
    return this.http.post(this.signupUrl, userData);
  }

  login(credentials: { email: string; password: string }): Observable<any> {
    return this.http.post(this.loginUrl, credentials).pipe(
      tap((res: any) => {
        // Save login data
        localStorage.setItem('token', res.token);
        localStorage.setItem('id', res.id);
        localStorage.setItem('role', res.role);
        this.roleSubject.next(res.role); // Notify role change
      })
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('id');
    localStorage.removeItem('role');
    this.roleSubject.next(null);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getUserRole(): string | null {
    return localStorage.getItem('role');
  }

  getUserId(): string | null {
    return localStorage.getItem('id');
  }
}

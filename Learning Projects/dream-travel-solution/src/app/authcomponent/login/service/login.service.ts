import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { LoginRequest } from '../../../model.class/class';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
 private apiUrl = 'http://localhost:8082/api/auth/login';

  constructor(private http: HttpClient) {}

  login(credentials: LoginRequest): Observable<any> {
    return this.http.post<any>(this.apiUrl, credentials)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An error occurred';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(() => new Error(errorMessage));
  }
}

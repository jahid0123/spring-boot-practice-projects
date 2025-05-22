import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { RegisterRequest } from '../../model.class/class';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
private apiUrl = 'http://localhost:8082/api/auth/register';

  constructor(private http: HttpClient) {}

  register(user: RegisterRequest): Observable<any> {
    return this.http.post<any>(this.apiUrl, user)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An error occurred';
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(() => new Error(errorMessage));
  }
}



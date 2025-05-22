import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  
   const publicEndpoints = [
    'http://localhost:8082/api/auth/login',
    'http://localhost:8082/api/auth/register'
  ];

  // Skip interceptor for public endpoints
  if (publicEndpoints.includes(req.url)) {
    return next(req);
  }

  // Get token from localStorage
  const token = localStorage.getItem('token');

  // Clone request and add Authorization header if token exists
  if (token) {
    const authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(authReq);
  }

  // Proceed without modifying the request if no token
  return next(req);
};

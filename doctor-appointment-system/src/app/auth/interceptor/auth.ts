import { HttpInterceptorFn } from "@angular/common/http";

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // Skip adding Authorization header for login and register endpoints
  const isAuthRequest =
    req.url.includes('/api/auth/login') ||
    req.url.includes('/api/auth/register') ||
    req.url.includes('/api/user/all/posted/property');

  if (isAuthRequest) {
    return next(req);
  }

  // Get token from AuthService
  const token = localStorage.getItem('token');

  // Add Authorization header if token exists
  if (token) {
    const clonedRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
    return next(clonedRequest);
  }

  // Proceed without modification if no token
  return next(req);
};

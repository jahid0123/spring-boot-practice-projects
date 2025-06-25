import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

export const authGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);
  const authService = inject(AuthService);

  const isLoggedIn = authService.isLoggedIn;
  const userRole = authService.getRole()?.toLowerCase() ?? '';

  const allowedRoles = route.data?.['roles'] as string[] | undefined;

  if (!isLoggedIn) {
    router.navigateByUrl('/login');
    return false;
  }

  if (allowedRoles && !allowedRoles.includes(userRole)) {
    router.navigateByUrl('/login'); // or maybe '/unauthorized'
    return false;
  }

  return true;
};

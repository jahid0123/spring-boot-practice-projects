import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../auth/service/auth.service';


export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const authService = inject(AuthService);

  const isLoggedIn = authService.isLoggedIn;
  const userRole = authService.getUserRole()?.toLowerCase() ?? '';

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
// export const authGuard: CanActivateFn = (route, state) => {
//   const router = inject(Router);
//   const authService = inject(AuthService);

//    const isLoggedIn = authService.isLoggedIn();
//   const expectedRoles = route.data['roles'] as Array<string>;
//   const userRole = authService.getUserRole()?.toLowerCase();

//   if (isLoggedIn && expectedRoles == undefined) {
//     return true;
//   } else if (isLoggedIn && expectedRoles.includes(userRole)) {
//     return true;
//   }

//   router.navigate(['/login']);
//   return false;
// };

// const expectedRoles = route.data['roles'] as Array<string>;
// const userRole = authService.getUserRole()?.toLowerCase() ?? '';

//   if (authService.isLoggedIn()) {
//     return true;
//   } else {
//     router.navigateByUrl('/login');
//     return false; 
//   }
// };
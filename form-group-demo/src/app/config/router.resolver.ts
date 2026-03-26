import { ResolveFn } from '@angular/router';

export const routerResolver: ResolveFn<boolean> = (route, state) => {
  return true;
};

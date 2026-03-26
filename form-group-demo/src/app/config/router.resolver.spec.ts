import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { routerResolver } from './router.resolver';

describe('routerResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => routerResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});

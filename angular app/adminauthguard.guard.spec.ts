import { TestBed } from '@angular/core/testing';

import { AdminAuthGuardGuard } from './adminauthguard.guard';

describe('AdminauthguardGuard', () => {
  let guard: AdminAuthGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AdminAuthGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { VmGuard } from './vm.guard';

describe('VmGuard', () => {
  let guard: VmGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(VmGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});

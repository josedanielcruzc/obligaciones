import { TestBed } from '@angular/core/testing';

import { ObligacionService } from './obligacion.service';

describe('ObligacionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ObligacionService = TestBed.get(ObligacionService);
    expect(service).toBeTruthy();
  });
});

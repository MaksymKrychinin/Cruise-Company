import { TestBed } from '@angular/core/testing';

import { TcDesignSystemService } from './tc-design-system.service';

describe('TcDesignSystemService', () => {
  let service: TcDesignSystemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TcDesignSystemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { FormateurService } from './formateur.service';

describe('FormateurServiceService', () => {
  let service: FormateurService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormateurService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { SousThemeService } from './sousTheme.service';

describe('Service: SousTheme', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SousThemeService]
    });
  });

  it('should ...', inject([SousThemeService], (service: SousThemeService) => {
    expect(service).toBeTruthy();
  }));
});

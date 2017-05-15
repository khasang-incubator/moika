import { TestBed, inject } from '@angular/core/testing';

import { WashFacilityService } from './wash-facility.service';

describe('WashFacilityService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WashFacilityService]
    });
  });

  it('should ...', inject([WashFacilityService], (service: WashFacilityService) => {
    expect(service).toBeTruthy();
  }));
});

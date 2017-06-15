import { TestBed, inject } from '@angular/core/testing';

import { InterfaceSwitchingService } from './interface-switching.service';

describe('InterfaceSwitchingService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [InterfaceSwitchingService]
    });
  });

  it('should be created', inject([InterfaceSwitchingService], (service: InterfaceSwitchingService) => {
    expect(service).toBeTruthy();
  }));
});

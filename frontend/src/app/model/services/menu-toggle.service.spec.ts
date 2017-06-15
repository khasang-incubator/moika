import { TestBed, inject } from '@angular/core/testing';

import { MenuToggleService } from './menu-toggle.service';

describe('MenuToggleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MenuToggleService]
    });
  });

  it('should be created', inject([MenuToggleService], (service: MenuToggleService) => {
    expect(service).toBeTruthy();
  }));
});

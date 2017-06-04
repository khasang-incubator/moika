import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityMapComponent } from './facility-map.component';

describe('FacilityMapComponent', () => {
  let component: FacilityMapComponent;
  let fixture: ComponentFixture<FacilityMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FacilityMapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FacilityMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

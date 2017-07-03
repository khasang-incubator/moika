import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityCalendarComponent } from './facility-calendar.component';

describe('FacilityCalendarComponent', () => {
  let component: FacilityCalendarComponent;
  let fixture: ComponentFixture<FacilityCalendarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FacilityCalendarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FacilityCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

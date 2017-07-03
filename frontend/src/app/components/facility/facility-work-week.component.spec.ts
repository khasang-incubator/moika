import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityWorkWeekComponent } from './facility-work-week.component';

describe('FacilityWorkWeekComponent', () => {
  let component: FacilityWorkWeekComponent;
  let fixture: ComponentFixture<FacilityWorkWeekComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FacilityWorkWeekComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FacilityWorkWeekComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

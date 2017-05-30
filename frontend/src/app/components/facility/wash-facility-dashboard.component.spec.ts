import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WashFacilityDashboardComponent } from './wash-facility-dashboard.component';

describe('WashFacilityDashboardComponent', () => {
  let component: WashFacilityDashboardComponent;
  let fixture: ComponentFixture<WashFacilityDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WashFacilityDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WashFacilityDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

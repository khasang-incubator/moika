import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WashFacilityComponent } from './wash-facility.component';

describe('WashFacilityComponent', () => {
  let component: WashFacilityComponent;
  let fixture: ComponentFixture<WashFacilityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WashFacilityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WashFacilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

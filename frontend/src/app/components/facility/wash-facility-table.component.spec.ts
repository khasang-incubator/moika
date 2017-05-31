import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WashFacilityTableComponent } from './wash-facility-table.component';

describe('WashFacilityTableComponent', () => {
  let component: WashFacilityTableComponent;
  let fixture: ComponentFixture<WashFacilityTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WashFacilityTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WashFacilityTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

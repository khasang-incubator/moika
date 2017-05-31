import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WashFacilityListComponent } from './wash-facility-list.component';

describe('WashFacilityListComponent', () => {
  let component: WashFacilityListComponent;
  let fixture: ComponentFixture<WashFacilityListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WashFacilityListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WashFacilityListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

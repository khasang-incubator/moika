import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityMainComponent } from './facility-main.component';

describe('FacilityMainComponent', () => {
  let component: FacilityMainComponent;
  let fixture: ComponentFixture<FacilityMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FacilityMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FacilityMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

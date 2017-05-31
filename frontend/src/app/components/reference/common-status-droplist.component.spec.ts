import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommonStatusDroplistComponent } from './common-status-droplist.component';

describe('CommonStatusDroplistComponent', () => {
  let component: CommonStatusDroplistComponent;
  let fixture: ComponentFixture<CommonStatusDroplistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommonStatusDroplistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommonStatusDroplistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommonStatusTableComponent } from './common-status-table.component';

describe('CommonStatusTableComponent', () => {
  let component: CommonStatusTableComponent;
  let fixture: ComponentFixture<CommonStatusTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommonStatusTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommonStatusTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

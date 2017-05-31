import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommonStatusSelectorComponent } from './common-status-selector.component';

describe('CommonStatusSelectorComponent', () => {
  let component: CommonStatusSelectorComponent;
  let fixture: ComponentFixture<CommonStatusSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommonStatusSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommonStatusSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

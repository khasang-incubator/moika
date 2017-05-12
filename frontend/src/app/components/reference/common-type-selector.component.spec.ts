import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommonTypeSelectorComponent } from './common-type-selector.component';

describe('CommonTypeSelectorComponent', () => {
  let component: CommonTypeSelectorComponent;
  let fixture: ComponentFixture<CommonTypeSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommonTypeSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommonTypeSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommonTypeDroplistComponent } from './common-type-droplist.component';

describe('CommonTypeDroplistComponent', () => {
  let component: CommonTypeDroplistComponent;
  let fixture: ComponentFixture<CommonTypeDroplistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommonTypeDroplistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommonTypeDroplistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

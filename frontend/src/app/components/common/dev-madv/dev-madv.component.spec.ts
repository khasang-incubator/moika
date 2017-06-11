import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DevMadvComponent } from './dev-madv.component';

describe('DevMadvComponent', () => {
  let component: DevMadvComponent;
  let fixture: ComponentFixture<DevMadvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DevMadvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DevMadvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

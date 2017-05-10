import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WashBoxDetailComponent } from './wash-box-detail.component';

describe('WashBoxDetailComponent', () => {
  let component: WashBoxDetailComponent;
  let fixture: ComponentFixture<WashBoxDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WashBoxDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WashBoxDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

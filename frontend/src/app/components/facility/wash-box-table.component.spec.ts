import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WashBoxTableComponent } from './wash-box-table.component';

describe('WashBoxTableComponent', () => {
  let component: WashBoxTableComponent;
  let fixture: ComponentFixture<WashBoxTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WashBoxTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WashBoxTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

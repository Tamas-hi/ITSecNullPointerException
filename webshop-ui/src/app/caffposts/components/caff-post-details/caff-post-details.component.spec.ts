import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaffPostDetailsComponent } from './caff-post-details.component';

describe('CaffPostDetailsComponent', () => {
  let component: CaffPostDetailsComponent;
  let fixture: ComponentFixture<CaffPostDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaffPostDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaffPostDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

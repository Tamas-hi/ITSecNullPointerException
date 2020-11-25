import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CafffilesearcherComponent } from './cafffilesearcher.component';

describe('CafffilesearcherComponent', () => {
  let component: CafffilesearcherComponent;
  let fixture: ComponentFixture<CafffilesearcherComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CafffilesearcherComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CafffilesearcherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

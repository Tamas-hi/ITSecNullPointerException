import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaffpostsComponent } from './caffposts.component';

describe('CaffpostsComponent', () => {
  let component: CaffpostsComponent;
  let fixture: ComponentFixture<CaffpostsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaffpostsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaffpostsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

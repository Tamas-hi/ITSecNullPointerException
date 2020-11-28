import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaffFileSearcherComponent } from './caff-file-searcher.component';

describe('CafffilesearcherComponent', () => {
  let component: CaffFileSearcherComponent;
  let fixture: ComponentFixture<CaffFileSearcherComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaffFileSearcherComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaffFileSearcherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

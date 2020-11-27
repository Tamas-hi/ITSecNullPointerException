import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CafffileuploadComponent } from './cafffileupload.component';

describe('CafffileuploadComponent', () => {
  let component: CafffileuploadComponent;
  let fixture: ComponentFixture<CafffileuploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CafffileuploadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CafffileuploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

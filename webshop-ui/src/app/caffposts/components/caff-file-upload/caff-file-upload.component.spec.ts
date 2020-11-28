import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CaffFileUploadComponent} from './caff-file-upload.component';

describe('CaffFileUploadComponent', () => {
  let component: CaffFileUploadComponent;
  let fixture: ComponentFixture<CaffFileUploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CaffFileUploadComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaffFileUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CaffPostsComponent} from './caff-posts.component';

describe('CaffPostsComponent', () => {
  let component: CaffPostsComponent;
  let fixture: ComponentFixture<CaffPostsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CaffPostsComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaffPostsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

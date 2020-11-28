import { TestBed } from '@angular/core/testing';

import { CaffPostsService } from './caff-posts.service';

describe('CaffPostsService', () => {
  let service: CaffPostsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CaffPostsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { SearchbarStatusService } from './searchbar-status.service';

describe('SearchbarStatusService', () => {
  let service: SearchbarStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchbarStatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { GetStudentDataService } from './get-student-data.service';

describe('GetStudentDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetStudentDataService = TestBed.get(GetStudentDataService);
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { AddStudentDataService } from './add-student-data.service';

describe('AddStudentDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddStudentDataService = TestBed.get(AddStudentDataService);
    expect(service).toBeTruthy();
  });
});

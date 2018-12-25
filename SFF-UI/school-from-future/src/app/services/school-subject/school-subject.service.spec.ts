import { TestBed } from '@angular/core/testing';

import { SchoolSubjectService } from './school-subject.service';

describe('SchoolSubjectService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SchoolSubjectService = TestBed.get(SchoolSubjectService);
    expect(service).toBeTruthy();
  });
});

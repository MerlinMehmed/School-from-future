import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubjectEventsComponent } from './subject-events.component';

describe('SubjectEventsComponent', () => {
  let component: SubjectEventsComponent;
  let fixture: ComponentFixture<SubjectEventsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubjectEventsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubjectEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

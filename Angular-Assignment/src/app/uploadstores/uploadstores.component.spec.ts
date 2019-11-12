import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadstoresComponent } from './uploadstores.component';

describe('UploadstoresComponent', () => {
  let component: UploadstoresComponent;
  let fixture: ComponentFixture<UploadstoresComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UploadstoresComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadstoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

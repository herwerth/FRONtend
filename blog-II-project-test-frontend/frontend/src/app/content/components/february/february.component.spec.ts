import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FebruaryComponent } from './february.component';

describe('FebruaryComponent', () => {
  let component: FebruaryComponent;
  let fixture: ComponentFixture<FebruaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FebruaryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FebruaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

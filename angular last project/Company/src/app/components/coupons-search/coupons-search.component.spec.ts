import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CouponsSearchComponent } from './coupons-search.component';

describe('CouponsSearchComponent', () => {
  let component: CouponsSearchComponent;
  let fixture: ComponentFixture<CouponsSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CouponsSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CouponsSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPurchaseCouponComponent } from './get-purchase-coupon.component';

describe('GetPurchaseCouponComponent', () => {
  let component: GetPurchaseCouponComponent;
  let fixture: ComponentFixture<GetPurchaseCouponComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetPurchaseCouponComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetPurchaseCouponComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

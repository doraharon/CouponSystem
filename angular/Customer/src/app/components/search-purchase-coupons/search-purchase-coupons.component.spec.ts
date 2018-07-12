import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPurchaseCouponsComponent } from './search-purchase-coupons.component';

describe('SearchPurchaseCouponsComponent', () => {
  let component: SearchPurchaseCouponsComponent;
  let fixture: ComponentFixture<SearchPurchaseCouponsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchPurchaseCouponsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchPurchaseCouponsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

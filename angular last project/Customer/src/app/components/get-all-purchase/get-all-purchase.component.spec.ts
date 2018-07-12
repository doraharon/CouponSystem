import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllPurchaseComponent } from './get-all-purchase.component';

describe('GetAllPurchaseComponent', () => {
  let component: GetAllPurchaseComponent;
  let fixture: ComponentFixture<GetAllPurchaseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetAllPurchaseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAllPurchaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

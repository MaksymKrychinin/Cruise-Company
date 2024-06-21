import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TcDesignSystemComponent } from './tc-design-system.component';

describe('TcDesignSystemComponent', () => {
  let component: TcDesignSystemComponent;
  let fixture: ComponentFixture<TcDesignSystemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TcDesignSystemComponent]
    });
    fixture = TestBed.createComponent(TcDesignSystemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

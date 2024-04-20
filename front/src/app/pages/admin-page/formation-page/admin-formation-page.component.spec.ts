import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminFormationComponent } from './admin-formation-page.component';

describe('AdminFormationComponent', () => {
  let component: AdminFormationComponent;
  let fixture: ComponentFixture<AdminFormationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminFormationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminFormationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

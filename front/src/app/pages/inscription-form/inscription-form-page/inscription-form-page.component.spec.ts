import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InscriptionFormPageComponent } from './inscription-form-page.component';

describe('InscriptionFormPageComponent', () => {
  let component: InscriptionFormPageComponent;
  let fixture: ComponentFixture<InscriptionFormPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InscriptionFormPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InscriptionFormPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

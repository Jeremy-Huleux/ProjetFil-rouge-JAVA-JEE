import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StagiaireFormulaireComponent } from './inscription-page.component';

describe('StagiaireFormulaireComponent', () => {
  let component: StagiaireFormulaireComponent;
  let fixture: ComponentFixture<StagiaireFormulaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StagiaireFormulaireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StagiaireFormulaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

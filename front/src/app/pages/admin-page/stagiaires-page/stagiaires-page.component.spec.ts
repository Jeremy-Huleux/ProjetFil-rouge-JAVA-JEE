import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StagiairesPageComponent } from './stagiaires-page.component';

describe('StagiairesPageComponent', () => {
  let component: StagiairesPageComponent;
  let fixture: ComponentFixture<StagiairesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StagiairesPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StagiairesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

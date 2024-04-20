import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Bootcamp } from '../../../models/bootcampmodel';
import Formation  from '../../../models/formation.model';
import { FormationService } from '../../../services/formation/formation.service';
import { RouterModule } from '@angular/router';
import { ProductAdminListComponent } from '../../products-page/product-admin-list/product-admin-list.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { SousTheme } from '../../../models/sousTheme.model';
import { SousThemeService } from '../../../services/SousTheme/sousTheme.service';


@Component({
  selector: 'app-admin-formation',
  templateUrl: './admin-formation-page.component.html',
  styleUrls: ['./admin-formation-page.component.css', "../../../../../node_modules/@fortawesome/fontawesome-free/css/all.css"],
  standalone: true,
  imports: [ProductAdminListComponent, RouterModule, ReactiveFormsModule, MatFormFieldModule]
})
export class AdminFormationComponent implements OnInit {
  formationForm: FormGroup = this.formBuilder.group({
    nom: ['', [Validators.required, Validators.pattern(/^[A-Za-z0-9\s]*$/)]],
    prix: ['', [Validators.required, Validators.pattern('^[0-9]*$')]],
    description: ['', [Validators.required, Validators.pattern(/^[A-Za-z0-9\s]*$/)]],
    img: [''],
    sousThemeId: ['']
  });


  submitted = false;
  formations: Formation[] = [];
  sousThemes: SousTheme[] = [];
  bootcamps: Bootcamp[] = [];
  formationCreate!: Formation;
  formationsCreates: Formation[] = [];
  afficherForm: boolean = false;
  formationSuppr!: Formation;
  constructor(
    private formBuilder: FormBuilder,
    private formationService: FormationService,
    private sousThemeService: SousThemeService
  ){};

  ngOnInit(): void {
      this.formationService.getFormations()
        .subscribe((formations: Formation[]) => 
          this.formations = formations
          );
      this.sousThemeService.getSousThemes()
        .subscribe((sousThemes: SousTheme[]) => 
          this.sousThemes = sousThemes
          );
  }
  onSubmit() {
    this.submitted = true;
    if (this.formationForm.invalid) {
      // Si le formulaire est invalide(ex: champs vide), retourne "false"
      return false;
    }else{
      // Sinon on ajoute le user et le "submit" repasse à false
      this.addFormation();
      this.submitted = false;
      // Reset du formulaire
      this.formationForm.reset();
      return true;
    }

  }
  get formationFormControls(): any {
    return this.formationForm.controls;
  }

  addFormation(): void {
  // On push les valeurs des inputs du formulaire
  this.formationsCreates.push(this.formationForm.value);

  // Appel du service pour la création
  this.formationService.createFormation(this.formationForm.value)
    .subscribe({
      next: (formationForm) => {
        // Si c'est un succès la formation est enregistré
        this.formationCreate = formationForm;
        this.formationService.getFormations()
        .subscribe((formations: Formation[]) => 
          this.formations = formations
          );
        // alert("Formation créé avec succès !");
      },
      error: (error) => {
          // Envoi une erreur si la création échoue
          alert("Erreur lors de la création de la formation");
          console.log(error);
        },
      complete: () => {
        console.log("Formation inséré");
      }
    });
  }

}

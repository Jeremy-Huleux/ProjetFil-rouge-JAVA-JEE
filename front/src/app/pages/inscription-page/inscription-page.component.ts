import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { User } from '../../models/user.model';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NavBarComponent } from "../../commons/navbar/nav-bar.component";
import { FooterComponent } from "../../commons/footer/footer.component";
import { RouterModule } from '@angular/router';
import { UserService } from '../../services/users/user.service';


@Component({
    selector: 'app-user-formulaire',
    standalone: true,
    templateUrl: './inscription-page.component.html',
    styleUrls: ['./inscription-page.component.css'],
    imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, NavBarComponent, FooterComponent, RouterModule]
})

export class UserFormulaireComponent {
  // Validateurs pour les champs du formulaire
  usersForm: FormGroup = this.formBuilder.group({
    nom: ['', Validators.required],
    prenom: ['', Validators.required],
    telephone: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    pseudo: ['', Validators.required],
    mdp: ['', [
      Validators.required,
      Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])/),
      Validators.minLength(8)
    ]],
  })

  // Soumission du formulaire "false" de base
  submitted: boolean = false;

  // Tableau de users
  users: any[] = [];
  // Un seul user
  user!: User;

  // Constructeur du formulaire
  constructor(private formBuilder: FormBuilder, private userService: UserService) {}; 

  // Méthode pour ajouter un user
  addUser(): void {
    // On push les valeurs des inputs du formulaire
    this.users.push(this.usersForm.value);
  
    // Appel du service pour la création
    this.userService.createUser(this.usersForm.value)
      .subscribe({
        next: (usersForm) => {
          // Si c'est un succès le user est enregistré
          this.user = usersForm;
          alert("User créé avec succès !");
        },
        error: (error) => {
          if (error.status === 409) {
            // Envoi une erreur "conflict" si l'email enregistré existe déjà en BDD
            alert("Erreur lors de la création du user : Email déjà utilisé.");
          } else {
            // Envoi une erreur si la création échoue
            alert("Erreur lors de la création du user");
            console.log(error);
          }
        },
        complete: () => {
          console.log("Inscription complete");
        }
      });
  }
  
  onSubmit() {
    // A la soumission on passe le submit à "true"
    this.submitted = true;

    if(this.usersForm.invalid) {
      // Si le formulaire est invalide(ex: champs vide), retourne "false"
      return false;
    } else {
      // Sinon on ajoute le user et le "submit" repasse à false
      this.addUser();
      this.submitted = false;
      // Reset du formulaire
      this.usersForm.reset();
      return true;
    }
    
  }

  // Pour contrôler les validators
  get form() {
    return this.usersForm.controls;
  }

}

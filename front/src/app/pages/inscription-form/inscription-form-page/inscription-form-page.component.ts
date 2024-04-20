import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { BootcampService } from '../../../services/bootcamp/bootcamp.service';
import { Bootcamp } from '../../../models/bootcamp.model';
import { Lieu } from '../../../models/lieu.model';
import Formation from '../../../models/formation.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-inscription-form-page',
  templateUrl: './inscription-form-page.component.html',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule],
  styleUrls: ['./inscription-form-page.component.css']
})
export class InscriptionFormPageComponent {
  user = { id: 0, nom: 'Nom Utilisateur', email: 'email@example.com' };
  bootcamp: Bootcamp = {
    id: 0,
    dateDebut: '',
    dateFin: '',
    statut: '',
    lieu: new Lieu(),
    formation: [],
    userCount: 0
  }; // Utilisez la classe Bootcamp
  inscriptionMessage: string = '';
  showFormForBootcampId: number | null = null;
  formation: Formation | null = null;

  constructor(
    private bootcampService: BootcampService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    // Récupérer le paramètre d'URL pour obtenir l'ID du bootcamp
    this.route.paramMap.subscribe(params => {
      const bootcampIdString = params.get('bootcampId');
      this.showFormForBootcampId = bootcampIdString ? parseInt(bootcampIdString, 10) : null; // Convertir en nombre ou laisser null
      // Charger les détails du bootcamp à partir du service en fonction de l'ID
      this.loadBootcampDetails();
    });
  }

  loadBootcampDetails() {
    // Utiliser le service pour récupérer les détails du bootcamp
    if (this.showFormForBootcampId) {
      this.bootcampService.getBootcampById(this.showFormForBootcampId)
        .subscribe({
          next: (result) => {
            this.bootcamp = result; // Mettre à jour le bootcamp avec les détails récupérés
            
            // Charger les formations liées au bootcamp
            this.loadFormations();
          },
          error: (error) => {
            console.error('Erreur lors de la récupération des détails du bootcamp', error);
          },
          complete: () => {
            console.log("Récupération complète");
          }
        });
    }
  }
  
  loadFormations() {
    if (this.bootcamp && this.bootcamp.id) {
      this.bootcampService.getFormationsInBootcamp(this.bootcamp.id)
        .subscribe({
          next: (formations) => {
            // Mettez à jour la propriété formation avec les données récupérées
            if (formations && formations.length > 0) {
              this.formation = formations[0]; // Si vous en attendez une seule, prenez la première
            }
          },
          error: (error) => {
            console.error('Erreur lors de la récupération des formations du bootcamp', error);
          },
          complete: () => {
            console.log("Récupération des formations complète");
          }
        });
    }
  }
  
  submitInscription() {
    // Assurez-vous que this.showFormForBootcampId est correctement défini
    if (this.showFormForBootcampId) {
      // Construisez l'objet de données à envoyer au backend
      const data = {
        bootcampId: this.showFormForBootcampId,
        userId: this.user.id, // Utilisez l'ID de l'utilisateur ici
        message: this.inscriptionMessage
      };
  
      // Envoyez les données au backend en utilisant le service HTTP Angular
      this.bootcampService.addUserToBootcamp(this.showFormForBootcampId, this.user.id)
        .subscribe({
          next: (_) => {
            this.showFormForBootcampId = null;
            this.inscriptionMessage = '';
          },
          error: (error) => {
            console.error('Erreur lors de la soumission de l\'inscription', error);
          },
          complete: () => {
            // Log de la complétion du processus d'inscription
            console.log("Inscription complète");
          
            // Afficher une alerte
            window.alert("Votre inscription a été validée. Vous serez recontacté prochainement pour des tests de positionnement.");
          
            // Redirection vers la page dédiée après l'inscription
            this.router.navigate(['/votre-page-dediee']);
          }
        });
    } else {
      console.error('ID du bootcamp non défini.');
    }
  }
  
  
}

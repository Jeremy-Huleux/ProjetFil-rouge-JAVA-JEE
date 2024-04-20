import { Component, Input, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { BootcampService } from '../../../services/bootcamp/bootcamp.service';
import { FormationService } from '../../../services/formation/formation.service';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import Formation from '../../../models/formation.model';
import { forkJoin } from 'rxjs/internal/observable/forkJoin';
import { UserService } from '../../../services/users/user.service';

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [MatButtonModule, MatCardModule, RouterModule, CommonModule, FormsModule],
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  @Input()
  product!: any;
  bootcamps: any[] = [];
  formationId!: number; 
  disabled: boolean = false;
  user = { nom: 'Nom Utilisateur', email: 'email@example.com' }; 
  inscriptionMessage: string = '';
  showFormForBootcampId: number | null = null;
  formation: Formation | null = null;

  constructor(
    private formationService: FormationService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router, 
  ) {}

  ngOnInit(): void {
    // Obtenez l'ID de la formation à partir de l'URL
    const formationIdString = this.route.snapshot.paramMap.get('id');
    this.formationId = formationIdString ? +formationIdString : 0;
    
    // Utilisez l'ID de la formation pour récupérer les bootcamps et les détails de la formation
    this.getBootcampsAndFormationDetails(this.formationId);
  } 

  getBootcampsAndFormationDetails(formationId: number): void {
    // Utilisez forkJoin pour effectuer plusieurs appels en parallèle
    forkJoin([
      this.formationService.getBootcampsInFormation(formationId),
      this.formationService.getFormation(formationId)
    ]).subscribe(([bootcamps, formation]) => {
      this.bootcamps = bootcamps;
      this.formation = formation;
    });
  }

  // Ajoutez la méthode pour sélectionner le bootcamp
  selectBootcampForInscription(bootcamp: any) {
    this.showFormForBootcampId = bootcamp.id;
    // Naviguez vers la page d'inscription avec l'ID du bootcamp
    this.router.navigate(['/inscriptionForm', { bootcampId: bootcamp.id }]);
  }
}

import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { RouterModule } from '@angular/router';
import Formation from '../../models/formation.model';
import { FormationService } from '../../services/formation/formation.service';


@Component({
    selector: 'app-home-page',
    standalone: true,
    templateUrl: './home-page.component.html',
    styleUrl: './home-page.component.css',
    imports: [MatCardModule,CommonModule,MatButtonModule,RouterModule]
})
export class HomePageComponent {

    formations: Formation[] = []
    productSlice: Formation[] = []
        //On créer un tableau contenant les façon d'appeler le CSS
    box: string[] = ["box one", "box two", "box three", "box four", "box five", "box six"];
    constructor(private formationService: FormationService) {}
    
        //on observe formations pour obtenir un tableau de Formation (que l'on recupère de la BDD)
    
    ngOnInit(): void {
      this.formationService.getFormations().subscribe((formations) => {
        this.formations = formations;
        this.productSlice = this.productSlising(formations);
    });
    }
        //On prend le tableau de formation et on choisit uniquement les 6 premieres

    productSlising(formations: Formation[]): Formation[]{
        return this.productSlice = this.formations.slice(0, 6);
    }

}

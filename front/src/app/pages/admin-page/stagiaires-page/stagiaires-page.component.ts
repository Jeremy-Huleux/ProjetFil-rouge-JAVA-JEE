import { Component } from '@angular/core';
import { Stagiaire } from '../../../models/stagiaires.model';
import { StagiaireService } from '../../../services/stagiaires/stagiaire.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-stagiaires-page',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './stagiaires-page.component.html',
  styleUrls: ['./stagiaires-page.component.css', "../../../../../node_modules/@fortawesome/fontawesome-free/css/all.css"]
})
export class StagiairesPageComponent {

  stagiaires!: Stagiaire[];

  constructor(private stagiaireService: StagiaireService) {}

  ngOnInit(): void {
    this.findStagiaires();
  }

  findStagiaires() {
    this.stagiaireService.getUserStagiaires().subscribe((data) => {
      this.stagiaires = data;
    })
  }

  supprimerUtilisateur(arg0: any) {
    throw new Error('Method not implemented.');
  }

  modifierUtilisateur(arg0: any) {
    throw new Error('Method not implemented.');
  }
}

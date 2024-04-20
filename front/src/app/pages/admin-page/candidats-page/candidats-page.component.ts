import { Component } from '@angular/core';
import { Candidats } from '../../../models/candidats.model';
import { CandidatsService } from '../../../services/candidats/candidats.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-candidats-page',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './candidats-page.component.html',
  styleUrls: ['./candidats-page.component.css', "../../../../../node_modules/@fortawesome/fontawesome-free/css/all.css"]
})
export class CandidatsPageComponent {

  candidats!: Candidats[];

  constructor(private candidatService: CandidatsService) {}

  ngOnInit(): void {
    this.findCandidats();
  }

  findCandidats() {
    this.candidatService.getCandidats().subscribe((data) => {
      this.candidats = data;
    })
  }

  supprimerUtilisateur(arg0: any) {
    throw new Error('Method not implemented.');
  }

  modifierUtilisateur(arg0: any) {
    throw new Error('Method not implemented.');
  }
}

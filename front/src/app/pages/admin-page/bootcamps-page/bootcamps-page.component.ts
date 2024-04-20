import { Component, OnInit } from '@angular/core';
import { BootcampService } from '../../../services/bootcamp/bootcamp.service';
import { Bootcamp } from '../../../models/bootcamp.model';
import { SearchBarComponent } from '../../../commons/search-bar/search-bar.component';
import Formation from '../../../models/formation.model';
import { FormationService } from '../../../services/formation/formation.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-bootcamps-page',
  standalone: true,
  templateUrl: './bootcamps-page.component.html',
  styleUrls: ['./bootcamps-page.component.css', "../../../../../node_modules/@fortawesome/fontawesome-free/css/all.css"],
  imports: [SearchBarComponent, RouterModule]
})
export class BootcampsPageComponent implements OnInit {
  bootcamps!: Bootcamp[];
  searchResults: Formation[] = [];
  isPresent: boolean = false;

  constructor(private bootcampService: BootcampService, private formationService: FormationService) {}

  ngOnInit(): void {
    this.findBootcamp();
  }

  findBootcamp() {
    this.bootcampService.getBootcamp().subscribe((data) => {
      this.bootcamps = data;

      // Pour chaque bootcamp, récupérer les formations liées
      this.bootcamps.forEach((bootcamp) => {
        this.bootcampService.getFormationsInBootcamp(bootcamp.id).subscribe((formations) => {
          bootcamp.formation = formations;
        });

        // Récupérer le nombre d'utilisateurs pour chaque bootcamp
        this.bootcampService.getUsersCountInBootcamps().subscribe((userCounts) => {
          bootcamp.userCount = userCounts[bootcamp.id] || 0;
        });
      });
    });
  }

  supprimerBootcamp(arg0: any) {
    throw new Error('Method not implemented.');
  }

  modifierBootcamp(arg0: any) {
    throw new Error('Method not implemented.');
  }
}

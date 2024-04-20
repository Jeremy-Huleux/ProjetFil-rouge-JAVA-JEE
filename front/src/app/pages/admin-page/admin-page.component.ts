import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterModule } from '@angular/router';
import { UserService } from '../../services/users/user.service';
import { LoginService } from '../../services/login/login.service';
import { User } from '../../models/user.model';
import { FormateurService } from '../../services/formateur/formateur.service';
import { FormationService } from '../../services/formation/formation.service';
import { BootcampService } from '../../services/bootcamp/bootcamp.service';


@Component({
    selector: 'app-admin-page',
    standalone: true,
    templateUrl: './admin-page.component.html',
    styleUrl: './admin-page.component.css',
    imports: [MatToolbarModule, MatIconModule, MatButtonModule, RouterModule]
})
export class AdminPageComponent {
    nbStagiaires: number = 0; 
    nbCandidats: number = 0;
    nbFormations: number = 0;
    nbFormateurs: number = 0;
    nbBootcamps: number = 0;
    isAuthentificated!: boolean;
    user!: User | undefined;

    constructor(private userService: UserService, private loginService: LoginService, private formateurService: FormateurService,
        private formationService: FormationService, private bootcampService: BootcampService) {}
    
    ngOnInit(): void {
        this.userService.getNbStagiaires()
        .subscribe({
            next: (nbStagiaires: number) => {
                this.nbStagiaires = nbStagiaires;
            },
            error: (error) => {
                console.error('Erreur lors de la récupération du nombre de stagiaires', error);
            },
            complete: () => {
                console.log("Récupération complète");
            }
        });

        this.userService.getNbCandidats()
        .subscribe({
            next: (nbCandidats: number) => {
                this.nbCandidats = nbCandidats;
            },
            error: (error) => {
                console.error('Erreur lors de la récupération du nombre de users', error);
            },
            complete: () => {
                console.log("Récupération complète");
            }
        });

        this.formationService.getFormationNb()
        .subscribe({
            next: (nbFormations: number) => {
                this.nbFormations = nbFormations;
            },
            error: (error) => {
                console.error('Erreur lors de la récupération du nombre de users', error);
            },
            complete: () => {
                console.log("Récupération complète");
            }
        });

        this.formateurService.getNbFormateurs()
        .subscribe({
            next: (nbFormateurs: number) => {
                this.nbFormateurs = nbFormateurs;
            },
            error: (error) => {
                console.error('Erreur lors de la récupération du nombre de stagiaires', error);
            },
            complete: () => {
                console.log("Récupération complète");
            }
        });

        this.bootcampService.getNbBootcamps()
        .subscribe({
            next: (nbBootcamps: number) => {
                this.nbBootcamps = nbBootcamps;
            },
            error: (error) => {
                console.error('Erreur lors de la récupération du nombre de bootcamps', error);
            },
            complete: () => {
                console.log("Récupération complète");
            }
        });

        // On s'inscrit à l'observable getLogin pour connaitre l'état de connexion en temps réel (Boolean)
        // On observe si la connexion change
        // On enregistre le User connecté dans la variable du composant 
        // (Getter d'un observable ouvert) dans login.service lors de la connexion
        // ToDo: appel l'objet user lors de la connexion
        this.loginService.getLogin.
        subscribe({
            next: (isLogged) => this.isAuthentificated = isLogged,
            error: (err) => console.error('Erreur au chargement', err)
        });

    }
        // ngDoCheck verifie si isAuthentificated change
        // si elle change on récupére la variable User pour la version local
    ngDoCheck():void{
        if(this.isAuthentificated){
        this.user = this.loginService.getLoginUser;
        }
    }
        

}
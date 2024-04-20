import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { LoginService } from '../../services/login/login.service';
import { User } from '../../models/user.model';
import { UserService } from '../../services/users/user.service';
import { LoginFormComponent } from '../../pages/connexion-page/connexion-page.component';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  providers: [User],
  imports: [RouterModule, LoginFormComponent],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent implements OnInit{
  img =  './../../../assets/images/logo.png';
  isAuthentificated!: boolean;
  user!: User | undefined;

  constructor(private loginService: LoginService, private userService: UserService) {}
  
      // On s'inscrit à l'observable getLogin pour connaitre l'état de connexion en temps réel (Boolean)
  ngOnInit(): void {
      // On observe si la connexion change
      // On enregistre le User connecter dans la variable du composant 
      // (Getter d'un observable ouvert) dans login.service lors de la connexion
      // ToDo: appel l'objet user lors de la connexion
    this.loginService.getLogin.
    subscribe({
      next: (isLogged) => this.isAuthentificated = isLogged,
      error: (err) => console.error('Erreur au chargement', err)
    });
  }
      // fonction de deconnexion
  deconnexion(): void {  
    if(this.isAuthentificated){
      this.loginService.logOut();
    }else{
      this.isAuthentificated = false;
    }
      // ToDo : appeller les fonction de "destroy"
  }
      // ngDoCheck verifie si isAuthentificated change
      // si elle change on récupére la variable User pour la version local
  ngDoCheck():void{
    if(this.isAuthentificated){
      this.user = this.loginService.getLoginUser;
    }
  }

}
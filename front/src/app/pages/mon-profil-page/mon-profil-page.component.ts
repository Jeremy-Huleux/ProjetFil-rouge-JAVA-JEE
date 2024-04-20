import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login/login.service';
import { UserService } from '../../services/users/user.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-mon-profil-page',
  templateUrl: './mon-profil-page.component.html',
  styleUrls: ['./mon-profil-page.component.css']
})
export class MonProfilPageComponent {

  isAuthentificated!: boolean;
  user!: User | undefined;

  constructor(private loginService: LoginService, private userService: UserService) {}
  
  //On s'inscrit à l'observable getLogin pour connaitre l'état de connexion en temps réel (Boolean)
  ngOnInit(): void {
    //On observe si la connexion change
    //On enregistre le User connecter dans la variable du composant 
    //(Getter d'un observable ouvert) dans login.service lors de la connexion
     //ToDo: appel l'objet user lors de la connexion
    this.loginService.getLogin.
    subscribe({
      next: (isLogged) => this.isAuthentificated = isLogged,
      error: (err) => console.error('Erreur au chargement', err)
    });
  }

  ngDoCheck():void{
    if(this.isAuthentificated){
      this.user = this.loginService.getLoginUser;
    }
  }
}

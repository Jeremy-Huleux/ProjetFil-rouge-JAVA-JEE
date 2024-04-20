import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs/internal/Observable';
import { User } from '../../models/user.model';
import { UserService } from '../users/user.service';
import { Router } from '@angular/router';



@Injectable({
  providedIn: 'root'
})
export class LoginService{
  private apiUrl = 'http://localhost:8080';
  // Pour suivre l'état de la conneisLoggedxion (true or false)
  private isLogged : BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  //private reponseBack!: Observable<any>;
  private user : User | undefined = undefined;
  
  // Observable permettant de surveiller l'état d'authentification
  isAuthenticated$: Observable<boolean> = this.isLogged.asObservable();
  reponseBack!: Observable<Object> | null;
  observable!: any;
  constructor(private httpClient: HttpClient, private userService: UserService, private route: Router) {}

  // Fonction pour effectuer la connexion
  login(email: string, mdp: string): Observable<Object>{
    this.reponseBack = null;
    const identite = { email, mdp };
    // Envoi de la requête HTTP POST pour la connexion
    // le Back répond par un objet user donc je définis <User> dans la reception de la réponse
    this.reponseBack =  this.httpClient.post<User>(`${this.apiUrl}/users/login`, identite);
    if(this.reponseBack != null){ // si le réponse n'est pas null alors on créer l'observable user
      this.observable = this.httpClient.post<User>(`${this.apiUrl}/users/login`, identite).subscribe({
        next: (reponse) => this.user = reponse,// un UserLoginDTO (sans mdp) si le serveur répond un userLoginDTO
        error: (err) => console.error('Erreur au chargement', err)
      });
    }
    return this.reponseBack;
  }
  // Fonction pour mettre à jour l'état d'authentification
  setLogin(value: boolean): void {
    this.isLogged.next(value);
  }
  //getter pour recupérer le boolean de login pour savoir si nous sommes connecter ou non
  get getLogin(){
    return this.isLogged.asObservable();
  }
  //Getter de user (private variable)
  get getLoginUser(){
    return this.user;
  }
  // Fonction de déconnexion
  logOut(): void {
    // on supprime l'utilisateur
    this.user = undefined;
    // Met à jour l'état d'authentification à false lors de la déconnexion
    this.setLogin(false);
    // on destroy l'observable pour eviter les fuites
    this.ngOnDestroy;
    // On redirige vers connexion
    this.route.navigate(['/connexion']);
  }
  ngOnDestroy(){
      this.observable.unsubscribe;
  }
  
  
  
  
  
  
}

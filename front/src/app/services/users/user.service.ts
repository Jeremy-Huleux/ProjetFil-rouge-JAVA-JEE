import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { User } from '../../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // Récupération de l'URL de Spring
  private apiUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  getUserByLogin(email: string): Observable<User>{
    return this.httpClient.get<User>(`${this.apiUrl}/users/${email}`);
  }
  // Récupère tous les stagiaires
  getUsers(): Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.apiUrl}/users/all`);
  }

  getUsersById(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.apiUrl}/users/${id}`);
  }
  // Récupère le nombre de stagiaires
  getNbStagiaires(): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/users/nbStagiaires`);
  }
  
  // Récupère le nombre de candidats
  getNbCandidats(): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/users/nbCandidats`);
  }

  getNbUsersByType(type: string): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/users/count/${type}`)
  }
  
  // Création d'un stagiaire
  createUser(user: User): Observable<User>{
    return this.httpClient.post<User>(`${this.apiUrl}/users/create`, user);
  }

}
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Stagiaire } from '../../models/stagiaire.model';

@Injectable({
  providedIn: 'root'
})
export class StagiaireService {
  // Récupération de l'URL de Spring
  private apiUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  getStagiaireByLogin(email: string): Observable<Stagiaire>{
    return this.httpClient.get<Stagiaire>(`${this.apiUrl}/stagiaires/${email}`);
  }
  // Récupère tous les stagiaires
  getStagiaires(): Observable<Stagiaire[]>{
    return this.httpClient.get<Stagiaire[]>(`${this.apiUrl}/stagiaires/all`);
  }

  getStagiairesById(id: number): Observable<Stagiaire> {
    return this.httpClient.get<Stagiaire>(`${this.apiUrl}/stagiaires/${id}`);
  }
  // Récupère le nombre de stagiaires
  getNbStagiaires(): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/stagiaires/nbStagiaires`);
  }
  
  // Récupère le nombre de candidats
  getNbCandidats(): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/stagiaires/nbCandidats`);
  }
  
  // Création d'un stagiaire
  createStagiaire(stagiaire: Stagiaire): Observable<Stagiaire>{
    return this.httpClient.post<Stagiaire>(`${this.apiUrl}/stagiaires/create`, stagiaire);
  }

}

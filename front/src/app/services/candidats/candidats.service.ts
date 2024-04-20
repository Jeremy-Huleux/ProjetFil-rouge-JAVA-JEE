import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Candidats } from '../../models/candidats.model';

@Injectable({
  providedIn: 'root'
})
export class CandidatsService {
  // Récupération de l'URL de Spring
  private apiUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  // Récupère tous les candidats
  getCandidats(): Observable<Candidats[]>{
    return this.httpClient.get<Candidats[]>(`${this.apiUrl}/users/all/candidats`);
  }

  // Récupère le nombre de candidats
  getNbCandidats(): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/users/nbCandidats`);
  }

}

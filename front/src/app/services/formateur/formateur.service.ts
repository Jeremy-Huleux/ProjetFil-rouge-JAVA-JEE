import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Formateurs } from '../../models/formateurs.model';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class FormateurService {
  // Récupération de l'URL de Spring
  private apiUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  getUserFormateurs(): Observable<Formateurs[]> {
    return this.httpClient.get<Formateurs[]>(`${this.apiUrl}/users/all/formateurs`);
  }
  
  // Récupère le nombre de formateurs
  getNbFormateurs(): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/users/nbFormateurs`);
  }

  // Création d'un formateur
  createUser(formateur: Formateurs): Observable<Formateurs>{
    return this.httpClient.post<Formateurs>(`${this.apiUrl}/users/create`, formateur);
  }

}

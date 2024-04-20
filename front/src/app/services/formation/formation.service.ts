import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import Formation from '../../models/formation.model';
import { SousThemeService } from '../SousTheme/sousTheme.service';
import { Bootcamp } from '../../models/bootcamp.model';

@Injectable({
  providedIn: 'root'
})
export class FormationService {

  private apiUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  getFormationById(id: number): Observable<Formation> {
    return this.httpClient.get<Formation>(`${this.apiUrl}/formations/${id}`);
  }

  getFormationNb(): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/formations/nbFormations`);
  }

  getFormations(): Observable<Formation[]> {
    return this.httpClient.get<Formation[]>(`${this.apiUrl}/formations/all`);
  }

  getFormation(id: number): Observable<Formation> {
    return this.httpClient.get<Formation>(`${this.apiUrl}/formations/${id}`)
  }

  createFormation(formation: Formation): Observable<Formation> {
    return this.httpClient.post<Formation>(`${this.apiUrl}/formations/create`,formation);
  }

  updateFormation(formation: Formation): Observable<Formation> {
    return this.httpClient.put<Formation>(`${this.apiUrl}/formations/${formation.id}`,formation);
  }

  deleteFormation(id: number): Observable<Formation> {
    return this.httpClient.delete<Formation>(`${this.apiUrl}/formations/delete/${id}`);
  }

  getFormationSousTheme(id: number): Observable<SousThemeService> {
    return this.httpClient.get<SousThemeService>(`${this.apiUrl}/formations/sousTheme/${id}`);
  }
    // Récupérer les bootcamps liés à une formation
    getBootcampsInFormation(formationId: number): Observable<Bootcamp[]> {
      return this.httpClient.get<Bootcamp[]>(`${this.apiUrl}/formations/${formationId}/bootcamps`);
    }
}
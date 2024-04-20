import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SousTheme } from '../../models/sousTheme.model';

@Injectable({
  providedIn: 'root'
})
export class SousThemeService {

  private apiUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) { }

  // getSousThemeByFormationsSousThemeId(id: number): Observable<SousTheme>{
  //   return this.httpClient.get<SousTheme>(`${this.apiUrl}/SousThemes/${id}`);
  // }

  getSousThemeById(id: number): Observable<SousTheme> {
    return this.httpClient.get<SousTheme>(`${this.apiUrl}/sousThemes/${id}`);
  }
  
  getSousThemeNb(): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/sousThemes/nbSousThemes`);
  }

  getSousThemes(): Observable<SousTheme[]> {
    return this.httpClient.get<SousTheme[]>(`${this.apiUrl}/sousThemes/all`);
  }

  getSousTheme(id: number): Observable<SousTheme> {
    return this.httpClient.get<SousTheme>(`${this.apiUrl}/sousThemes/${id}`)
  }

  createSousTheme(SousTheme: SousTheme): Observable<SousTheme> {
    return this.httpClient.post<SousTheme>(`${this.apiUrl}/sousThemes`,SousTheme);
  }

  updateSousTheme(SousTheme: SousTheme): Observable<SousTheme> {
    return this.httpClient.put<SousTheme>(`${this.apiUrl}/sousThemes/${SousTheme.id}`,SousTheme);
  }

  deleteSousTheme(id: number): Observable<SousTheme> {
    return this.httpClient.delete<SousTheme>(`${this.apiUrl}/sousThemes/delete/${id}`);
  }

}

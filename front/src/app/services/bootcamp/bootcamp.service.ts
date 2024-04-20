import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Bootcamp } from '../../models/bootcamp.model';
import Formation from '../../models/formation.model';
import { User } from '../../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class BootcampService {
  private apiUrl = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  // Récupère les bootcamps par leur id
  getBootcampById(id: number): Observable<Bootcamp> {
    return this.httpClient.get<Bootcamp>(`${this.apiUrl}/bootcamps/find/${id}`);
  }

  // Récupère les bootcamps
  getBootcamp(): Observable<Bootcamp[]> {
    return this.httpClient.get<Bootcamp[]>(`${this.apiUrl}/bootcamps/all`);
  }

  // Récupère le nombre de bootcamps
  getNbBootcamps(): Observable<number> {
    return this.httpClient.get<number>(`${this.apiUrl}/bootcamps/nbBootcamps`);
  }

  // Afficher les formations liées à un bootcamp
  getFormationsInBootcamp(bootcampId: number): Observable<Formation[]> {
    return this.httpClient.get<Formation[]>(`${this.apiUrl}/bootcamps/${bootcampId}/formations`);
  }

  // Récupérer le nombre d'utilisateurs liés à un bootcamp
  getUsersCountInBootcamps(): Observable<any> {
    return this.httpClient.get<any>(`${this.apiUrl}/bootcamps/nbUsers`);
  }  

  estPresentDansFormation(bootcampId: number, formationId: number): Observable<boolean> {
    const url = `${this.apiUrl}/${bootcampId}/isPresentDansFormation/${formationId}`;
    return this.httpClient.get<boolean>(url);
  }

  // Création d'un bootcamp
  createBootcamp(bootcamp: Bootcamp): Observable<Bootcamp> {
    return this.httpClient.post<Bootcamp>(`${this.apiUrl}/bootcamps/createBootcamp`, bootcamp);
  }

  // Ajouter un user à un bootcamp
  addUserToBootcamp(bootcampId: number, userId: number): Observable<string> {
    const url = `${this.apiUrl}/bootcamps/${bootcampId}/addUser`;
    
    return this.httpClient.post<string>(url, { userId });
  }

}

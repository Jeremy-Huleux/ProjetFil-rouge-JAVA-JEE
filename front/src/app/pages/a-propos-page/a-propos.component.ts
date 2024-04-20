import { Component, OnInit } from '@angular/core';
import { LoginFormComponent } from '../connexion-page/connexion-page.component';
import { LoginService } from '../../services/login/login.service';
import { User } from '../../models/user.model';
import { UserService } from '../../services/users/user.service';



@Component({
  selector: 'app-a-propos',
  standalone: true,
  imports: [LoginFormComponent],
  templateUrl: './a-propos.component.html',
  styleUrl: './a-propos.component.css'
})
export class AProposComponent implements OnInit{

  //test pour récupéré les données de l'utilisateur et l'afficher dans une page
  isAuthentificated: boolean = false;
  user!: User | undefined;
  constructor(private loginService: LoginService, private userService: UserService) {}
  
  ngOnInit(): void {
        
    if(this.user){this.user = this.loginService.getLoginUser;}
    this.loginService.getLogin.
    subscribe({
      next: (isLogged) => this.isAuthentificated = isLogged,
      error: (err) => console.error('Erreur au chargement', err)
    });
  }


}

import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { NavBarComponent } from "./commons/navbar/nav-bar.component";
import { FooterComponent } from "./commons/footer/footer.component";


@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, NavBarComponent, FooterComponent]
})
export class AppComponent {
  title = 'filrouge';

  // Constructeur
  constructor(private router: Router) {}

  // Méthode pour vérifier si l'URL contient "Dashboard"
  isDashboardRoute(): boolean {
    return this.router.url.includes('/dashboard');
  }

}

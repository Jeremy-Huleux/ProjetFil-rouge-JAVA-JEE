import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ProductDetailsComponent } from "../product-details/product-details.component";
import Formation from '../../../models/formation.model';
import { FormationService } from '../../../services/formation/formation.service';
import { ProductCardComponent } from "../product-card/product-card.component";

@Component({
    selector: 'app-product-page',
    standalone: true,
    templateUrl: './product-page.component.html',
    styleUrl: './product-page.component.css',
    imports: [ProductDetailsComponent, RouterModule, ProductCardComponent]
})
export class ProductPageComponent {


  product!: Formation ;

  constructor(
      private router: Router, 
      private route:ActivatedRoute, 
      private formationService: FormationService 
  ) {}

  private subscribeFormation(id: number): void {
    this.formationService.getFormation(id).subscribe({
      next: (formation) => this.product = formation,
      error: (err) => console.error('Erreur au chargement', err)
    });
  }

  private setSubscribe(type: string | null, id: string | null): void {
    if(type === 'formations' && id){
      this.subscribeFormation(+id);
    } else {
      this.router.navigate(['/not-found']);
    }
  }
  

  ngOnInit(): void {
    const type =this.route.snapshot.paramMap.get('type');
    const id = this.route.snapshot.paramMap.get('id');
    this.setSubscribe(type,id);
  }

}

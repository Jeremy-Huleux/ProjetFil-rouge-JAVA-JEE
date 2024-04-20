import { Component } from '@angular/core';
import { ProductListComponent } from "./product-list/product-list.component";
import Formation from '../../models/formation.model';
import { FormationService } from '../../services/formation/formation.service';

@Component({
    selector: 'app-products-page',
    standalone: true,
    templateUrl: './products-page.component.html',
    styleUrl: './products-page.component.scss',
    imports: [ProductListComponent]
})
export class ProductsPageComponent {

  formations: Formation[] = []

  constructor(private formationService: FormationService) {}
  ngOnInit(): void {
    this.formationService.getFormations().subscribe((formations) => {this.formations = formations;});
  }

}

import { Component, Input } from '@angular/core';
import { ProductCardComponent } from "../product-card/product-card.component";

@Component({
    selector: 'app-product-list',
    standalone: true,
    templateUrl: './product-list.component.html',
    styleUrl: './product-list.component.css',
    imports: [ProductCardComponent]
})
export class ProductListComponent {
  @Input()
  products: any[] = []
}

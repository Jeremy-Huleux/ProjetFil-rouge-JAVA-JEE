import { Component, Input, OnInit } from '@angular/core';
import { ProductCardComponent } from '../product-card/product-card.component';

@Component({
  selector: 'app-product-admin-list',
  standalone: true,
  templateUrl: './product-admin-list.component.html',
  styleUrls: ['./product-admin-list.component.css'],
  imports: [ProductCardComponent]
})
export class ProductAdminListComponent implements OnInit {

  @Input()
  products: any[] = []


  constructor() { }

  ngOnInit() {
  }

}

import { Component, Output, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  standalone: true,
  styleUrls: ['./search-bar.component.css'],
  imports: [FormsModule]
})
export class SearchBarComponent {
  @Output() searchEvent = new EventEmitter<string>();
  searchTerm: string = '';

  search() {
    this.searchEvent.emit(this.searchTerm);
  }

  onSearchChange(): void {
    this.searchEvent.emit(this.searchTerm);
  }
  
}

import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent {

  @Input() totalPages: number;
  @Input() currentPage: number;
  @Output() pageIsSelected: EventEmitter<number>;
  constructor() {
    this.pageIsSelected = new EventEmitter();
  }

  existNextPage():boolean {
    return this.currentPage+1 < this.totalPages;
  }

  pageSelected(newPage: number) {
    if (newPage >= 0 && newPage <= this.totalPages) {
      this.currentPage = newPage;
      this.pageIsSelected.emit(newPage);
    }
  }

}

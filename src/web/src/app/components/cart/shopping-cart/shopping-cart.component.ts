import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';
import { ShoppingCartService } from 'src/app/services/shopping-cart.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  books: Book[];
  quantities: number[];
  total = 0;

  constructor(private cartService: ShoppingCartService, private bookService: BookService) { }

  ngOnInit(): void {
    this.books = this.cartService.getAllBooks();
    this.quantities = this.cartService.getAllQuantities();

    this.calculate();
  }

  purchase() {
      this.cartService.purchase();
      //this.total=0;
  }

  delete(refLivre: string): void {
    this.books = this.books.filter(book => book.refLivre != refLivre)
    localStorage.setItem('books',JSON.stringify(this.books)); 
  }

  calculate(): void {
    if(this.books != null)
    for(let i=0; i<this.books.length; i++) {
        this.cartService.calculate(this.books[i].refLivre, this.quantities[i]).subscribe(res => {
            this.total += res;
        });
    }
  }


}

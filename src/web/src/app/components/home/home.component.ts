import { Component, OnInit } from '@angular/core';
import { Book } from '../../models/book';
import { BookService } from '../../services/book.service';
import { ShoppingCartService } from 'src/app/services/shopping-cart.service';
import { ShoppingCartComponent } from '../cart/shopping-cart/shopping-cart.component';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

    books: Book[] = [];

    constructor(private bookService: BookService, private cartService: ShoppingCartService) { }

    ngOnInit(): void {
        this.bookService.getAll().subscribe(books => {
            this.books = books;
        });
    }

    delete(refLivre: string): void {
        this.bookService.delete(refLivre).subscribe(() => {
            console.log('book deleted')
            //localStorage.setItem('books',JSON.stringify(this.books));
        });

    }

    addToCart(refLivre: string,quantity: number): void {
        this.bookService.get(refLivre).subscribe(book => {
            this.cartService.add(book,quantity);
        });
    }

}

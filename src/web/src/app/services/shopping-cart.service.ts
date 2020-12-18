import { Injectable } from '@angular/core';
import { Book } from '../models/book';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ShoppingCartService {

  uri: string = 'http://localhost:8017/BookStore/api/bookStore';

  books: Book[] = [];
  quantities: number[] = [];

  constructor(private http: HttpClient) { }

  add(book: Book, quantity: number) {
    this.books.push(book);
    this.quantities.push(quantity);
    localStorage.setItem('books',JSON.stringify(this.books));
    localStorage.setItem('quantities',JSON.stringify(this.quantities));
  }

  getAllBooks(): Book[] {
    return JSON.parse(localStorage.getItem('books'));
    return this.books;
  }

  getAllQuantities(): number[] {
    return JSON.parse(localStorage.getItem('quantities'));
    return this.quantities;
  }

  calculate(refLivre?: string, qte?: number) {
    return this.http.get<number>(`${this.uri}/calculerPrix/${refLivre}/${qte}`);
  }

  purchase() {
    localStorage.removeItem('books');
    localStorage.removeItem('quantities');
  }

}

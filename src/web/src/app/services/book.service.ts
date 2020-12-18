import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs';
import {Book} from "../models/book"

@Injectable({
    providedIn: 'root'
})
export class BookService {
    uri: string = 'http://localhost:8017/BookStore/api/bookStore';

    constructor(private http: HttpClient) { }

    getAll(): Observable<Book[]> {
        console.log('get all service');
        return this.http.get<Book[]>(`${this.uri}/getAll`);
    }

    get(refLivre: string): Observable<Book> {
        return this.http.get<Book>(`${this.uri}/getLivre/${refLivre}`);
    }

    add(refLivre: string, titre: string, auteur: string, dateSortie: string, quantite: number, prixUnitaire: number): Observable<Book> {
        console.log('add service');
        let book: Book = new Book(refLivre,titre,auteur,dateSortie,quantite,prixUnitaire);
        return this.http.post<Book>(`${this.uri}/ajouterLivre`,book);
    }

    delete(refLivre: string) {
        console.log('delete service');
        //let params = new HttpParams().set("refLivre",refLivre.toString());
        return this.http.get(`${this.uri}/supprimerLivre/${refLivre}`);  
    }

    update(refLivre: string, titre: string, auteur: string, dateSortie: string, quantite: number, prixUnitaire: number): Observable<Book> {
        console.log('update service');
        let book: Book = new Book(refLivre,titre,auteur,dateSortie,quantite,prixUnitaire);
        return this.http.post<Book>(`${this.uri}/modifierLivre`,book);
    }

}

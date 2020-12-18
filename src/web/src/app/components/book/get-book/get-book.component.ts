import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-get-book',
  templateUrl: './get-book.component.html',
  styleUrls: ['./get-book.component.css']
})
export class GetBookComponent implements OnInit {

    msgRetour ="";
    colorMsgRetour="";

    book: Book = {refLivre: '', titre: '', auteur: '', dateSortie: '', quantite: null, prixUnitaire:null};

    constructor(private route: ActivatedRoute, private bookService: BookService) { }

    ngOnInit(): void {

        this.msgRetour=""
        this.colorMsgRetour="alert alert-light"

        this.route.params.subscribe(params => {
            this.bookService.get(params.refLivre).subscribe(book => {
                this.book = book;
            })
        })
    }

    update(refLivre: string, titre: string, auteur: string, dateSortie: string, quantite: number, prixUnitaire: number): void {
        this.bookService.update(refLivre,titre,auteur,dateSortie,quantite,prixUnitaire).subscribe((book) => {
            console.log('Book added !',book);
        })
        this.msgRetour=" Livre modifié avec succés ";
        this.colorMsgRetour="alert alert-success"
    }

}

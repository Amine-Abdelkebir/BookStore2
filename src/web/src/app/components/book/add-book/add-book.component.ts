import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  msgRetour ="";
  colorMsgRetour="";

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.msgRetour=""
    this.colorMsgRetour="alert alert-light"
  }

  add(refLivre: string, titre: string, auteur: string, dateSortie: string, quantite: number,prixUnitaire: number,event: Event): void {
    event.preventDefault();
      this.bookService.add(refLivre,titre,auteur,dateSortie,quantite,prixUnitaire).subscribe((book) => {
          console.log('Book added !',book);    
      })
      this.msgRetour=" Livre ajouté avec succés ";
      this.colorMsgRetour="alert alert-success"
  }

}

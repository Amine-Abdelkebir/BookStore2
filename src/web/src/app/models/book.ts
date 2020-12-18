
export class Book {
    refLivre: string;
    titre: string;
    auteur: string;
    dateSortie: string;
    quantite : number;
    prixUnitaire : number;

    constructor(refLivre, titre, auteur, dateSortie, quantite, prixUnitaire ){
        this.refLivre=refLivre;
        this.titre=titre;
        this.auteur=auteur;
        this.dateSortie=dateSortie;
        this.quantite=quantite;
        this.prixUnitaire=prixUnitaire;
    }
   }
   
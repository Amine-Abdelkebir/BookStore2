package org.bookStore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Livre")
public class Livre{
	
	@Id
	@Column(name="refLivre")
	String refLivre;
	
	@Column(name="titre")
	String titre;	
	
	@Column(name="auteur")
	String auteur;
	
	@Column(name="dateSortie")
	String dateSortie;
	
	@Column(name="quantite")
	int quantite;
	
	@Column(name="prixUnitaire")
	long prixUnitaire;
	
	
	public Livre() {
		super();
	}
	
	public Livre (String refLivre, String titre, String auteur, String dateSortie, int quantite, long prixUnitaire){
		super();
		this.refLivre=refLivre;
		this.titre=titre;
		this.auteur=auteur;
		this.dateSortie=dateSortie;
		this.quantite=quantite;
		this.prixUnitaire=prixUnitaire;
	}

	public String getRefLivre() {
		return refLivre;
	}

	public void setRefLivre(String refLivre) {
		this.refLivre = refLivre;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public long getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(long prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	
}
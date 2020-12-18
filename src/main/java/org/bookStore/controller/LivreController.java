package org.bookStore.controller;

import java.util.List;

import org.bookStore.model.Livre;
import org.bookStore.service.LivreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bookStore")

public class LivreController {
	
	@Autowired
	LivreServiceInterface livreServiceInterface;
	
	// Juste pour tester l'api :
	@RequestMapping(value = "/testApi", method = RequestMethod.GET, produces = "application/json")
    public String testApi(){
        return "Testing REST API !";
    }
	
	// Retourne tous les livres :
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<Livre> getLivres() {		
		List<Livre> listOfLivres = livreServiceInterface.getAllLivres();		
		return listOfLivres;
	}

	// Retourne un livre par son référence :
	@RequestMapping(value = "/getLivre/{refLivre}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Livre getLivreByRefLivre(@PathVariable String refLivre) {
		return livreServiceInterface.getLivre(refLivre);
	}

	// Ajout de livre :
	@RequestMapping(value = "/ajouterLivre", method = RequestMethod.POST, headers = "Accept=application/json")
	public String ajouterLivre(@RequestBody Livre liv) {	
		livreServiceInterface.addLivre(liv);		
		return "Livre ajoute avec succes !";
	}
	
	// Modification de livre :
	@RequestMapping(value = "/modifierLivre", method = RequestMethod.POST, headers = "Accept=application/json")
	public String modifierLivre(@RequestBody Livre liv) {
		String msgRetour="";
		if(liv.getRefLivre().equals(null)){
			msgRetour="Livre n'existe pas !";
		}
		else{	
			livreServiceInterface.updateLivre(liv);
			msgRetour="Livre modifier avec succes !";
		}
		return msgRetour;
	}
	
	// Supprimer un livre
	@RequestMapping(value = "/supprimerLivre/{refLivre}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String supprimerLivre(@PathVariable String refLivre) {
		livreServiceInterface.deleteLivre(refLivre);
		 return "Livre supprimer avec succes !";
	}
	
	// Calcule prix total avec quantité donnée :
	@RequestMapping(value = "/calculerPrix/{refLivre}/{quantite}", method = RequestMethod.GET, headers = "Accept=application/json")
	public double calculerPrix(@PathVariable String refLivre, @PathVariable int quantite) {
		double montant = livreServiceInterface.calculerPrix(refLivre, quantite);
		return montant;
	}
		 
	// Calcule somme prix unitaire des livres choisi :
	@RequestMapping(value = "/calSommePrixTotal", method = RequestMethod.POST, headers = "Accept=application/json")
	public double calSommePrixTotal(@RequestBody List<String> listRef) {	
		double montant = livreServiceInterface.calSommePrixTotal(listRef);		
		return montant;
	}
		
}

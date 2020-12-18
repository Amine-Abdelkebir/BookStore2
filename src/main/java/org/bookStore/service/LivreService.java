package org.bookStore.service;

import java.util.List;

import org.bookStore.dao.LivreDAOInterface;
import org.bookStore.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LivreService implements LivreServiceInterface {

	@Autowired
	LivreDAOInterface livreDAOInterface;
	
	
	public LivreService() {
	}
	
	public LivreService(LivreDAOInterface livreDAOInterface) {
		this.livreDAOInterface = livreDAOInterface;
	}
	
	
	@Transactional
	public List<Livre> getAllLivres() {
		return livreDAOInterface.getAllLivres();
	}

	@Transactional
	public Livre getLivre(String refLivre) {
		return livreDAOInterface.getLivre(refLivre);
	}

	@Transactional
	public void addLivre (Livre liv) {
		livreDAOInterface.addLivre(liv);
	}
	
	@Transactional
	public void updateLivre(Livre liv) {
		livreDAOInterface.updateLivre(liv);

	}

	@Transactional
	public void deleteLivre(String refLivre) {
		livreDAOInterface.deleteLivre(refLivre);
	}
	
	@Transactional
	public double calculerPrix(String refLivre, int quantite) {
		double montant = livreDAOInterface.calculerPrix(refLivre, quantite);
		return montant;
	}

	@Transactional
	public double calSommePrixTotal(List<String> listRef) {
		double montant = livreDAOInterface.calSommePrixTotal(listRef);		
		return montant;
	}
	
}

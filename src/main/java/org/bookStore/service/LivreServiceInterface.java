package org.bookStore.service;

import java.util.List;

import org.bookStore.model.Livre;
import org.springframework.transaction.annotation.Transactional;

public interface LivreServiceInterface {

	@Transactional
	public List<Livre> getAllLivres();

	@Transactional
	public Livre getLivre(String refLivre);

	@Transactional
	public void addLivre (Livre liv);
	
	@Transactional
	public void updateLivre(Livre liv);

	@Transactional
	public void deleteLivre(String refLivre);
	
	@Transactional
	public double calculerPrix(String refLivre, int quantite);
	
	@Transactional
	public double calSommePrixTotal(List<String> listRef);
	
}

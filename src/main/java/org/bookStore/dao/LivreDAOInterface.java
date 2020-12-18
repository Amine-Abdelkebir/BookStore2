package org.bookStore.dao;

import java.util.List;

import org.bookStore.model.Livre;


public interface LivreDAOInterface {

	public List<Livre> getAllLivres();
	
	public Livre getLivre(String refLivre);

	public Livre addLivre (Livre liv);
	
	public void updateLivre(Livre liv);

	public void deleteLivre(String refLivre);
	
	public double calculerPrix(String refLivre, int quantite);
	
	public double calSommePrixTotal(List<String> listRef);
	
}

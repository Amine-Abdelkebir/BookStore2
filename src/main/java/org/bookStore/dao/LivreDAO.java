package org.bookStore.dao;

import java.util.List;

import org.bookStore.model.Livre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class LivreDAO implements LivreDAOInterface {

	@Autowired
	private SessionFactory sessionFactory; 

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<Livre> getAllLivres() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Livre> listLivre = session.createQuery("from Livre").list();
		
		for (Livre c:listLivre) {
			System.out.println("#Ref = "+c.getRefLivre());
		}		
		return listLivre;
	}

	public Livre getLivre(String refLivre) {
		Session session = this.sessionFactory.getCurrentSession();
		Livre livre = (Livre) session.get(Livre.class, new String(refLivre));
		return livre;
	}

	public Livre addLivre (Livre liv) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(liv);
		return liv;
	}
	
	public void updateLivre(Livre liv) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(liv);
	}

	public void deleteLivre(String refLivre) {
		Session session = this.sessionFactory.getCurrentSession();
		Livre l = (Livre) session.load(Livre.class, new String(refLivre));
		if (null != l) {
			session.delete(l);
		}
	}
	
	public double calculerPrix(String refLivre, int quantite) {
		long prixUnitaire = getLivre(refLivre).getPrixUnitaire();
		long montant=prixUnitaire*quantite;
		return montant;
	}
	
	public double calSommePrixTotal(List<String> listRef) {
		double montant=0;

		for (int i=0;i<listRef.size();i++) {
			String ref = listRef.get(i);
			montant= montant+getLivre(ref).getPrixUnitaire();		
		}		
		return montant;
	}
	
}

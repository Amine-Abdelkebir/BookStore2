
package org.bookStore.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bookStore.dao.LivreDAOInterface;
import org.bookStore.model.Livre;
import org.bookStore.service.LivreService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;



class LivreServiceTest {

	@Mock
	private LivreDAOInterface mockedLivreDAO;
	private LivreService livreService;
	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		
		this.mockedLivreDAO = mock(LivreDAOInterface.class);
		this.livreService = new LivreService(this.mockedLivreDAO);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void testGetAllLivres() throws Exception {
		ArrayList<Livre> listeLivre = new ArrayList<Livre>();
		
		listeLivre.add(new Livre("123","livre1","auteur1","12/12/2000",12,15000));
		listeLivre.add(new Livre("456","livre2","auteur2","12/12/2000",50,7500));
		
		when(this.mockedLivreDAO.getAllLivres()).thenReturn(listeLivre);
		when (livreService.getAllLivres()).thenReturn ((List)listeLivre);
		assertTrue(this.livreService.getAllLivres().size() == 2);
	}
	
	

	@Test
	void testGetLivre() {
		when(this.mockedLivreDAO.getLivre("123")).thenReturn(new Livre("123","livre1","auteur1","12/12/2000",12,15000));
		assertTrue(this.livreService.getLivre("123").getRefLivre() == "123");
	}
	
	
	@Test
	void testAddLivre() {
		Livre livre = new Livre("123","livre1","auteur1","12/12/2000",12,15000);
        
		livreService.addLivre(livre);
   
        verify(mockedLivreDAO, times(1)).addLivre(livre);
		
	}
	

	@Test
	void testUpdateLivre() {
		Livre livre = new Livre("123","livre1","auteur1","12/12/2000",12,15000);
        
		livreService.updateLivre(livre);
   
        verify(mockedLivreDAO, times(1)).updateLivre(livre);
	}
	

	@Test
	void testDeleteLivre() {
		Livre livre = new Livre("123","livre1","auteur1","12/12/2000",12,15000);
        
		livreService.deleteLivre(livre.getRefLivre());
   
        verify(mockedLivreDAO, times(1)).deleteLivre(livre.getRefLivre());
	}
	

	@Test
	void testCalculerPrix() {
		Livre livre = new Livre("123","livre1","auteur1","12/12/2000",12,15000);
		int quantite=2;
        
		when(livreService.calculerPrix(livre.getRefLivre(), quantite)).thenReturn((double) 30000);
		
		assertTrue(mockedLivreDAO.calculerPrix(livre.getRefLivre(), quantite) == 30000,"Calcul non valide !");
	}

  
	@Test
	void testCalSommePrixTotal() throws Exception {
		
		Livre livre = new Livre("123","livre1","auteur1","12/12/2000",12,1000);
		Livre livre2 = new Livre("789","livre1","auteur1","12/12/2000",12,1000);
		
		ArrayList<String> listeRef = new ArrayList<String>();
		listeRef.add(livre.getRefLivre());
		listeRef.add(livre2.getRefLivre());
        
		when(livreService.calSommePrixTotal(listeRef)).thenReturn((double) 2000);
		
		assertTrue(mockedLivreDAO.calSommePrixTotal(listeRef) == 2000,"Calcul non valide !");

	}
}



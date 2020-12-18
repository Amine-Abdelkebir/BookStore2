package org.bookStore.test;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.bookStore.controller.LivreController;
import org.bookStore.model.Livre;
import org.bookStore.service.LivreServiceInterface;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.minidev.json.JSONArray;


class LivreControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private LivreServiceInterface mockedLivreService;
	
	@InjectMocks
	private LivreController livreController;
	
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(livreController).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void testGetLivres() throws Exception {
		System.out.println(" ****  TEST METHOD 'GetLivres' **** ");
		
		ArrayList<Livre> listeLivre = new ArrayList<Livre>();
		listeLivre.add(new Livre("123","livre1","auteur1","12/12/2000",12,15000));
		listeLivre.add(new Livre("456","livre2","auteur2","12/12/2000",50,7500));
		
		when(mockedLivreService.getAllLivres()).thenReturn(listeLivre);
		
		this.mockMvc.perform(get("/api/bookStore/getAll"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$",hasSize(2)))
		.andDo(print());
	}


	@Test
	void testGetLivreByRefLivre() {
		System.out.println(" ****  TEST METHOD 'GetLivreByRefLivre' **** ");
		
		when(this.mockedLivreService.getLivre("123")).thenReturn(new Livre("123","livre1","auteur1","12/12/2000",12,15000));
		assertTrue(this.livreController.getLivreByRefLivre("123").getRefLivre() == "123");
	}
	

	@Test
	void testAjouterLivre() {
		System.out.println(" ****  TEST METHOD 'AjouterLivre' **** ");
		
		Livre livre = new Livre("123","livre1","auteur1","12/12/2000",12,15000);
        
		livreController.ajouterLivre(livre);
   
        verify(mockedLivreService, times(1)).addLivre(livre);

	}
	

	@Test
	void testModifierLivre() {
		System.out.println(" ****  TEST METHOD 'ModifierLivre' **** ");
		
		Livre livre = new Livre("123","livre1","auteur1","12/12/2000",12,15000);
        
		livreController.modifierLivre(livre);
   
        verify(mockedLivreService, times(1)).updateLivre(livre);

	}
	

	@Test
	void testSupprimerLivre() {
		System.out.println(" ****  TEST METHOD 'SupprimerLivre' **** ");
		
		Livre livre = new Livre("123","livre1","auteur1","12/12/2000",12,15000);
        
		livreController.supprimerLivre(livre.getRefLivre());
   
        verify(mockedLivreService, times(1)).deleteLivre(livre.getRefLivre());

	}
	

	@Test
	void testCalculerPrix() throws Exception {
		System.out.println(" ****  TEST METHOD 'CalculerPrix' **** ");
		
		Livre livre = new Livre("123","livre1","auteur1","12/12/2000",12,15000);
		int quantite=2;
        
		when(mockedLivreService.calculerPrix(livre.getRefLivre(), quantite)).thenReturn((double) 30000);
				
		this.mockMvc.perform(get("/api/bookStore/calculerPrix/123/2"))
		.andExpect(status().isOk())
		.andDo(print());
		
		assertTrue(mockedLivreService.calculerPrix(livre.getRefLivre(), quantite) == 30000,"Calcul non valide !");

	}
	
	
	@Test
	void testCalSommePrixTotal() throws Exception {
		System.out.println(" ****  TEST METHOD 'CalSommePrixTotal' **** ");
		
		Livre livre = new Livre("123","livre1","auteur1","12/12/2000",12,8900);
		Livre livre2 = new Livre("789","livre1","auteur1","12/12/2000",12,12500);
		
		ArrayList<String> listeRef = new ArrayList<String>();
		listeRef.add(livre.getRefLivre());
		listeRef.add(livre2.getRefLivre());
		
	    String jsonList = JSONArray.toJSONString(listeRef);
	    //System.out.println(jsonList);
        
		when(mockedLivreService.calSommePrixTotal(listeRef)).thenReturn((double) 21400);
				
		this.mockMvc.perform(post("/api/bookStore/calSommePrixTotal").contentType(MediaType.APPLICATION_JSON).content(jsonList) )
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print())
			.andReturn();
		
		assertTrue(mockedLivreService.calSommePrixTotal(listeRef) == 21400,"Calcul non valide !");

	}

}

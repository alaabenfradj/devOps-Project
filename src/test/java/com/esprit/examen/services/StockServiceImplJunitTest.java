package com.esprit.examen.services;


import java.text.SimpleDateFormat;
import java.util.*;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.esprit.examen.entities.Stock;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
 class StockServiceImplJunitTest {

	@Autowired
	StockServiceImpl stockServiceJunit ;
	@Autowired
	IProduitService produitServiceJunit ;
	@Autowired
	ProduitRepository produitRepository ;
	@Autowired
	CategorieProduitServiceImpl categorieProduitService ;
	private final Produit p = Produit.builder().codeProduit("12_3A").libelleProduit("SICAM").dateCreation(new Date()).dateDerniereModification(new Date()).prix(4300).build();
	private final Stock s =Stock.builder().libelleStock("stock_mock").qte(10).qteMin(3).build();

	@RepeatedTest(1)
	@Order(1)
	 void testAddStock() {
		int expectedNumberOfStocks = stockServiceJunit.retrieveAllStocks().size();
		log.info("stocks in database before adding : " + expectedNumberOfStocks);
		//adding new stock
		Stock savedStock= stockServiceJunit.addStock(s);
		assertEquals(expectedNumberOfStocks+1, stockServiceJunit.retrieveAllStocks().size());
		assertNotNull(savedStock.getLibelleStock());

		//categorie produit
		CategorieProduit cp = CategorieProduit.builder().codeCategorie("CP_22A").libelleCategorie("SICAM_CP").build();
		CategorieProduit categorie = categorieProduitService.addOrUpdateCategorieProduit(cp);
		//adding a product
		p.setCategorieProduit(categorie);
		Produit produit = produitServiceJunit.addProduit(p);
		assertNotNull(produit);
		assertEquals(p.getCodeProduit(),produit.getCodeProduit());
		//assign produit to stock
		produitServiceJunit.assignProduitToStock(produit.getIdProduit(),savedStock.getIdStock());
		//check if product is assigned to stock
		 Produit productAfterAssignment = produitServiceJunit.retrieveProduit(produit.getIdProduit());
		 Stock stockAssignedToProduct = productAfterAssignment.getStock();
		 assertNotNull(stockAssignedToProduct);
		 assertNotNull(productAfterAssignment);
		 assertEquals(stockAssignedToProduct.getIdStock(),savedStock.getIdStock());
	}
	@Test
	@Order(2)
	 void testDeleteStock() {
		Integer expectedNumberOfStocks = stockServiceJunit.retrieveAllStocks().size();
		log.info("stocks in database before deleting : " + expectedNumberOfStocks);
		//we're kinda sure that the stocks are not empty because the addstock method will run before this method
		assertNotNull(expectedNumberOfStocks);
		List<Produit> products = produitServiceJunit.retrieveAllProduits();
		assertNotNull(products);
		for (Stock s : stockServiceJunit.retrieveAllStocks()){
			for (Produit p : products){
				if (p.getStock() == null || p.getStock().getIdStock() == s.getIdStock()){
					produitServiceJunit.deleteProduit(p.getIdProduit());
				}
			}
			//delete stock eventually !
			stockServiceJunit.deleteStock(s.getIdStock());
		}
		assertEquals(0 ,stockServiceJunit.retrieveAllStocks().size() );

	}




}

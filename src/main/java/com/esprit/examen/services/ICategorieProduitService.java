package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.CategorieProduit;


public interface ICategorieProduitService {

	List<CategorieProduit> retrieveAllCategorieProduits();

	CategorieProduit addOrUpdateCategorieProduit(CategorieProduit cp);

	void deleteCategorieProduit(Long id);


	CategorieProduit retrieveCategorieProduit(Long id);

}

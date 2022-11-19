package com.esprit.examen.dtos;

import com.esprit.examen.entities.Produit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategorieProduitDTO{
    private Long idCategorieProduit;
    private String codeCategorie;
    private String libelleCategorie;
    private Set<Produit> produits;
}

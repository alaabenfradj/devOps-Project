package com.esprit.examen.dtos;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Produit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailFactureDTO  {

    private Long idDetailFacture;
    private Integer qteCommandee;
    private float prixTotalDetail;
    private Integer pourcentageRemise;
    private float montantRemise;
    private Produit produit;
    Facture facture;

}
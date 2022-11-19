package com.esprit.examen.dtos;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Stock;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDTO {
    private Long idProduit;
    private String codeProduit;
    private String libelleProduit;
    private float prix;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Temporal(TemporalType.DATE)
    private Date dateDerniereModification;


    private Stock stock;


    private Set<DetailFacture> detailFacture;

    private CategorieProduit categorieProduit;

}

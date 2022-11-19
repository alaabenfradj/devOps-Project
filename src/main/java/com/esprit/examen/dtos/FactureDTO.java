package com.esprit.examen.dtos;

import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Reglement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FactureDTO {
    private Long idFacture;
    private float montantRemise;
    private float montantFacture;
    @Temporal(TemporalType.DATE)
    private Date dateCreationFacture;
    @Temporal(TemporalType.DATE)
    private Date dateDerniereModificationFacture;
    private Boolean archivee;

    private Set<DetailFacture> detailsFacture;

    private Fournisseur fournisseur;

    private Set<Reglement> reglements;

}

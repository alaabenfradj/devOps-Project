package com.esprit.examen.dtos;

import com.esprit.examen.entities.Fournisseur;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailFournisseurDTO {
    private Long idDetailFournisseur;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date dateDebutCollaboration;
    private String adresse;
    private String matricule;

    private Fournisseur fournisseur;
}

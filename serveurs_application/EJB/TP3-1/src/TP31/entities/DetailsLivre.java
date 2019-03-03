package TP31.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "details_livre")
public class DetailsLivre implements Serializable {

    @Id
    private String isbn;

    private int nbpages;

    private int annee_parution;

    public int getNbPages() {
        return this.nbpages;
    }
}

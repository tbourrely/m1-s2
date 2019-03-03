package TP31.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "livre_tp31")
public class Livre implements Serializable {

    @Id
    private String isbn;

    private String titre;

    @OneToOne
    @PrimaryKeyJoinColumn
    private DetailsLivre details;


    public DetailsLivre getDetails() {
        return this.details;
    }
}

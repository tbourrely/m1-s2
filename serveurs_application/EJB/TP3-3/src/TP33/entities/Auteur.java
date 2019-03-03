package TP33.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "auteur")
public class Auteur implements Serializable {

    @Id
    private int num;

    private String nom;

    @OneToMany(mappedBy = "auteur")
    private Collection<Livre> livres;

    public String getNom() {
        return this.nom;
    }

    public Collection<Livre> getLivres() {
        return this.livres;
    }
}

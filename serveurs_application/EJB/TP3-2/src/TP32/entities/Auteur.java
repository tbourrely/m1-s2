package TP32.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "auteur")
public class Auteur implements Serializable {

    @Id
    private int num;

    private String nom;

    public String getNom() {
        return this.nom;
    }
}

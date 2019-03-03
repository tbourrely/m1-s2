package TP33.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "livre_tp32")
public class Livre implements Serializable {

    @Id
    private String isbn;

    private String titre;

    @ManyToOne
    @JoinColumn(name = "auteur_num", referencedColumnName = "num")
    private Auteur auteur;

    public Auteur getAuteur() {
        return this.auteur;
    }

    // Partie B
    public void setAuteur(Auteur nAuteur) {
        this.auteur = nAuteur;
    }

    public String getTitre() {
        return this.titre;
    }
}

/*
-- Partie A
UPDATE livre_tp32 SET auteur_num=1 WHERE isbn='111';
UPDATE livre_tp32 SET auteur_num=1 WHERE isbn='222';
-- Partie B
INSERT INTO livre_tp32(isbn, titre) VALUES('597', 'Les Miserables');
INSERT INTO auteur(num, nom) VALUES(2, 'Victor Hugo');
 */

package TP32.stateless;

import TP32.entities.Auteur;
import TP32.entities.Livre;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GestionLivreBean implements GestionLivre {

    @PersistenceContext(unitName="EjbComponentPU")
    private EntityManager entityManager;

    public GestionLivreBean() {
    }

    @Override
    public String getAuteur(String isbn) {
        Livre livre = this.entityManager.find(Livre.class, isbn);
        return livre.getAuteur().getNom();
    }

    @Override
    public void setAuteur(String isbn, int num_auteur) {
        Livre livre = this.entityManager.find(Livre.class, isbn);
        Auteur auteur = this.entityManager.find(Auteur.class, num_auteur);
        livre.setAuteur(auteur);
    }

    @Override
    public void unsetAuteur(String isbn) {
        Livre livre = this.entityManager.find(Livre.class, isbn);
        livre.setAuteur(null);
    }
}

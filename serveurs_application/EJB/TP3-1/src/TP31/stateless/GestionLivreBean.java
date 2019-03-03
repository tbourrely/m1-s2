package TP31.stateless;

import TP31.entities.Livre;

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
    public int getNbPages(String isbn) {
        Livre l = this.entityManager.find(Livre.class, isbn);
        return l.getDetails().getNbPages();
    }
}

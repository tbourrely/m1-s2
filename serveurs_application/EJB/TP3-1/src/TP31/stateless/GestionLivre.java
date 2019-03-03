package TP31.stateless;

import javax.ejb.Remote;

@Remote
public interface GestionLivre {
    int getNbPages(String isbn);
}

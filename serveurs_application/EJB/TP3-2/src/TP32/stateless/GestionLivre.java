package TP32.stateless;

import javax.ejb.Remote;

@Remote
public interface GestionLivre {
    String getAuteur(String isbn);
    void setAuteur(String isbn, int num_auteur); // Partie B
    void unsetAuteur(String isbn); // Partie C
}

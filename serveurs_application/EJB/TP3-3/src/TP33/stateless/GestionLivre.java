package TP33.stateless;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote
public interface GestionLivre {
    String getAuteur(String isbn);
    void setAuteur(String isbn, int num_auteur); // Partie B
    void unsetAuteur(String isbn); // Partie C
    ArrayList<String> getLivresAuteur(String isbn);
}

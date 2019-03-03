

import TP33.stateless.GestionLivre;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;

public class TP3Client3 {
    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            GestionLivre gestionLivre = (GestionLivre)initialContext.lookup("TP33.stateless.GestionLivre");

            ArrayList<String> titres = gestionLivre.getLivresAuteur("111");

            System.out.println("Livres du meme auteur que [111] : ");
            for (String t : titres) {
                System.out.println(t);
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

import TP32.stateless.GestionLivre;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TP3Client2 {
    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            GestionLivre gestionLivre = (GestionLivre)initialContext.lookup("TP32.stateless.GestionLivre");

            // Partie A
            String auteur111 = gestionLivre.getAuteur("111");
            String auteur222 = gestionLivre.getAuteur("222");
            System.out.println("Auteur[111] : " + auteur111);
            System.out.println("Auteur[222] : " + auteur222);

            // Partie B
            gestionLivre.setAuteur("597", 2);
            System.out.println("Auteur[597] : " + gestionLivre.getAuteur("597"));

            // Partie C
            gestionLivre.unsetAuteur("597");

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

/*
UPDATE livre_tp32 SET auteur_num=NULL WHERE isbn='597';
 */

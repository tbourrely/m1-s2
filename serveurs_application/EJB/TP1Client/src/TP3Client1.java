import TP31.stateless.GestionLivre;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TP3Client1 {
    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            GestionLivre gestionLivre = (GestionLivre)initialContext.lookup("TP31.stateless.GestionLivre");
            int nbPages111 = gestionLivre.getNbPages("111");
            int nbPages7337 = gestionLivre.getNbPages("7337");

            System.out.println("nbPages[111] : " + nbPages111);
            System.out.println("nbPages[7337] : " + nbPages7337);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

import TP31.stateless.GestionLivre;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class TP3Client1 {
    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            GestionLivre gestionLivre = (GestionLivre)initialContext.lookup("TP31.stateless.GestionLivre");

            Scanner input = new Scanner(System.in);

            while(1==1) {
                System.out.println("isbn : ");
                String isbn = input.next();

                int nbPages = gestionLivre.getNbPages(isbn);

                System.out.println("nb pages [" + isbn + "] : " + nbPages);

            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

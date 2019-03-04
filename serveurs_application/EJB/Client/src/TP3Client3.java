

import TP33.stateless.GestionLivre;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Scanner;

public class TP3Client3 {
    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            GestionLivre gestionLivre = (GestionLivre)initialContext.lookup("TP33.stateless.GestionLivre");

            Scanner input = new Scanner(System.in);

            while (1==1) {
                System.out.println("ISBN : ");
                String isbn = input.next();
                ArrayList<String> titres = gestionLivre.getLivresAuteur(isbn);
                System.out.println("Livres du meme auteur que [" + isbn + "] : ");
                for (String t : titres) {
                    System.out.println(t);
                }
            }

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

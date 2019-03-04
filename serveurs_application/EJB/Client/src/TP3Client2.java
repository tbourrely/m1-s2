import TP32.stateless.GestionLivre;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class TP3Client2 {
    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            GestionLivre gestionLivre = (GestionLivre)initialContext.lookup("TP32.stateless.GestionLivre");

            Scanner input = new Scanner(System.in);

            while (1==1) {
                System.out.println("getAuteur : 0");
                System.out.println("setAuteur : 1");
                System.out.println("unsetAuteur : 2");

                int choice = input.nextInt();

                if (choice == 0) {
                    System.out.println("ISBN : ");
                    String isbn = input.next();
                    String auteur = gestionLivre.getAuteur(isbn);
                    System.out.println("Auteur ["+isbn+"] : " + auteur);
                }

                if (choice == 1) {
                    System.out.println("ISBN : ");
                    String isbn = input.next();
                    System.out.println("Auteur : ");
                    int auteur = input.nextInt();
                    gestionLivre.setAuteur(isbn, auteur);
                }

                if (choice == 2) {
                    System.out.println("ISBN : ");
                    String isbn = input.next();
                    gestionLivre.unsetAuteur(isbn);
                }
            }

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

/*
UPDATE livre_tp32 SET auteur_num=NULL WHERE isbn='597';
 */

import stateless.BookManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class BookClient {
    public static void main(String[] args) {
        try {
            InitialContext context = new InitialContext();
            BookManager manager = (BookManager)context.lookup("stateless.BookManager");

            Scanner input = new Scanner(System.in);

            while (1==1) {

                System.out.println("Emprunter : 0");
                System.out.println("Rendre : 1");
                System.out.println("Ajouter : 2");
                System.out.println("Supprimer : 3");

                int choice = input.nextInt();

                if (choice == 0) {
                    System.out.println("ISBN : ");
                    String isbn = input.next();
                    manager.borrowBook(isbn);
                }

                if (choice == 1) {
                    System.out.println("ISBN : ");
                    String isbn = input.next();
                    manager.unborrowBook(isbn);
                }

                if (choice == 2) {
                    System.out.println("ISBN : ");
                    String isbn = input.next();
                    System.out.println("TITLE : ");
                    String title = input.next();
                    manager.newBook(isbn, title);
                }

                if (choice == 3) {
                    System.out.println("ISBN : ");
                    String isbn = input.next();
                    manager.removeBook(isbn);
                }
            }

        } catch(NamingException e) {
            e.printStackTrace();
        }
    }
}

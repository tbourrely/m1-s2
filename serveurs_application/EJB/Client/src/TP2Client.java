import stateful.BorrowManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class TP2Client {
    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            BorrowManager bm = (BorrowManager)initialContext.lookup("stateful.BorrowManager");
            Scanner input = new Scanner(System.in);

            while(1==1) {
                System.out.println("Creer emprunt : 0");
                System.out.println("Emprunter : 1");
                System.out.println("Rendre : 2");

                int choice = input.nextInt();

                if (choice == 0) {
                    System.out.println("Emprunteur : ");
                    int borrower = input.nextInt();
                    bm.createBorrow(borrower);
                }

                if (choice == 1) {
                    System.out.println("ISBN : ");
                    String isbn = input.next();
                    bm.borrowBook(isbn);
                }

                if (choice == 2) {
                    System.out.println("ISBN : ");
                    String isbn = input.next();
                    bm.returnBook(isbn);
                }
            }

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

/*
DELETE FROM livre_emp WHERE isbn='666';
UPDATE livre SET dispo=1 WHERE isbn='666';
UPDATE emprunteur SET nblivresemp=0 WHERE numemp=2;
UPDATE emprunteur SET nblivresemp=0 WHERE numemp=1;
 */

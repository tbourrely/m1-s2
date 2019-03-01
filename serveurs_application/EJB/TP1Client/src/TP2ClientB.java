import TP2.entities.Book;
import stateless.InfosBook;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

public class TP2ClientB {
    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            InfosBook ib = (InfosBook) initialContext.lookup("stateless.InfosBook");
            System.out.println("Title book[666] = " + ib.getTitle("666"));

            List<Book> availableBooks = ib.availableBooks();

            System.out.println("-----------------------");
            System.out.println("    Available Books    ");
            System.out.println("-----------------------");
            for (Book b : availableBooks) {
                System.out.println(b.getTitle());
            }
            System.out.println("-----------------------");

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

import entities.Book;
import stateless.BookManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BookClient {
    public static void main(String[] args) {
        try {
            InitialContext context = new InitialContext();
            BookManager manager = (BookManager)context.lookup("stateless.BookManager");
            manager.newBook("TEST", "TestHea");
            manager.removeBook("TEST");

            manager.newBook("979", "NonEmprunte");
            manager.unborrowBook("979");

            manager.newBook("980", "Emprunte");
            manager.unborrowBook("980");
            manager.borrowBook("980");

        } catch(NamingException e) {
            e.printStackTrace();
        }
    }
}

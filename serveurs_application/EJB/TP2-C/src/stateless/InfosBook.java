package stateless;

import TP2.entities.Book;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface InfosBook {
    String getTitle(String isbn);
    List<Book> availableBooks();
}

package stateless;

import entities.Book;

import javax.ejb.Remote;

@Remote
public interface BookManager {
    void newBook(String isbn, String title);
    void removeBook(String isbn);
    void borrowBook(String isbn);
    void unborrowBook(String isbn);
    Book findById(String id);
}

package stateful;

import javax.ejb.Remote;

@Remote
public interface BorrowManager {
    void createBorrow(int numemp);
    void borrowBook(String isbn);
    void returnBook(String isbn);
}

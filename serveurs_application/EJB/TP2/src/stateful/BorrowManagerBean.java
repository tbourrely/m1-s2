package stateful;

import entities.Book;
import entities.Borrow;
import entities.Borrower;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful(name = "BorrowManagerEJB")
public class BorrowManagerBean implements BorrowManager {

    private Borrower borrower;

    @PersistenceContext(unitName="EjbComponentPU")
    private EntityManager entityManager;

    public BorrowManagerBean() {
    }

    @Override
    public void createBorrow(int numemp) {
        this.borrower = this.entityManager.find(Borrower.class, numemp);
    }

    @Override
    public void borrowBook(String isbn) {
        Book book = this.entityManager.find(Book.class, isbn);
        this.borrower = this.entityManager.merge(this.borrower);

        if (null != book && book.isAvailable()) {
            Borrow borrow = new Borrow(isbn, book.getTitle(), this.borrower.getNumemp());
            this.borrower.setNbBorrowedBooks(this.borrower.getNbBorrowedBooks() + 1);
            book.borrow();
            this.entityManager.persist(borrow);
            this.entityManager.flush();
        }
    }

    @Override
    public void returnBook(String isbn) {
        Borrow borrow = this.entityManager.find(Borrow.class, isbn);
        Book book = this.entityManager.find(Book.class, isbn);
        this.borrower = this.entityManager.merge(this.borrower);

        if (null != borrow && null != book) {
            this.borrower.setNbBorrowedBooks(this.borrower.getNbBorrowedBooks() - 1);
            book.unborrow();
            this.entityManager.remove(borrow);
            this.entityManager.flush();
        }
    }
}

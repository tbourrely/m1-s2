package stateful;

import TP2.entities.Book;
import TP2.entities.Borrow;
import TP2.entities.Borrower;
import customExceptions.EmprunteurInvalide;
import customExceptions.LivreDejaEmprunte;
import customExceptions.NbMaxEmpruntsAtteint;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class BorrowManagerBean implements BorrowManager {

    private Borrower borrower;

    private int MAX_EMPRUNT = 3;

    @PersistenceContext(unitName="EjbComponentPU")
    private EntityManager entityManager;

    public BorrowManagerBean() {
    }

    @Override
    public void createBorrow(int numemp) throws EmprunteurInvalide {
        this.borrower = this.entityManager.find(Borrower.class, numemp);

        if (null == this.borrower) {
            throw new EmprunteurInvalide();
        }
    }

    @Override
    public void borrowBook(String isbn) throws NbMaxEmpruntsAtteint, LivreDejaEmprunte {
        Book book = this.entityManager.find(Book.class, isbn);
        this.borrower = this.entityManager.merge(this.borrower);

        if (null != book) {

            if (!book.isAvailable()) {
                throw new LivreDejaEmprunte();
            }

            if (this.borrower.getNbBorrowedBooks() >= this.MAX_EMPRUNT) {
                throw new NbMaxEmpruntsAtteint();
            }

            Borrow borrow = new Borrow(isbn, book.getTitle(), this.borrower.getNumemp());
            this.borrower.borrowBook();
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

        if (null != borrow && null != book && !book.isAvailable() && borrow.getBorrowerId() == this.borrower.getNumemp()) {
            this.borrower.returnBook();
            book.unborrow();
            this.entityManager.remove(borrow);
            this.entityManager.flush();
        }
    }
}

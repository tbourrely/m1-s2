package stateless;

import entities.Book;

import javax.ejb.Stateless;
        import javax.persistence.EntityManager;
        import javax.persistence.PersistenceContext;

@Stateless
public class BookManagerBean implements BookManager {

    @PersistenceContext(unitName="EjbComponentPU")
    private EntityManager entityManager;

    @Override
    public void newBook(String isbn, String title) {
        Book book = new Book(isbn, title);
        this.entityManager.persist(book);
    }

    @Override
    public void removeBook(String isbn) {
        Book b = this.findById(isbn);

        if (null != b) {
            this.entityManager.remove(b);
            this.entityManager.flush();
        }
    }

    @Override
    public void borrowBook(String isbn) {
        Book b = this.findById(isbn);

        if (null != b) {
            b.borrow();
        }
    }

    @Override
    public void unborrowBook(String isbn) {
        Book b = this.findById(isbn);

        if (null != b) {
            b.unborrow();
        }
    }

    public Book findById(String id) {
        return this.entityManager.find(Book.class, id);
    }
}

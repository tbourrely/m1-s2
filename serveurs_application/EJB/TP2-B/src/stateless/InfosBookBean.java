package stateless;

import TP2.entities.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "InfosBookEJB")
public class InfosBookBean implements InfosBook {

    @PersistenceContext(unitName = "EjbComponentPU")
    private EntityManager entityManager;

    public InfosBookBean() {
    }

    @Override
    public String getTitle(String isbn) {

        String title = this.entityManager
                            .createNamedQuery("getTitleByISBN", Book.class)
                            .setParameter("isbn", isbn)
                            .getSingleResult()
                            .getTitle();


        return title;
    }

    @Override
    public List<Book> availableBooks() {
        List<Book> books = this.entityManager
                                .createNamedQuery("getAvailableBooks", Book.class)
                                .getResultList();

        return books;
    }
}

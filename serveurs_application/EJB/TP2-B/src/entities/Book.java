package TP2.entities;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(
                name = "getTitleByISBN",
                query = "SELECT b FROM Book as b WHERE b.isbn =:isbn"
        ),
        @NamedQuery(
                name = "getAvailableBooks",
                query = "SELECT b FROM Book AS b WHERE b.dispo = 1"
        )
})

@Entity
@Table(name="livre")
public class Book implements Serializable {

    @Id
    private String isbn;

    @Column(name="titre")
    private String title;

    private int dispo; // 0 || 1

    public Book()
    {}

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
        this.dispo = 1;
    }

    public void borrow() {
        this.dispo = 0;
    }

    public void unborrow() {
        this.dispo = 1;
    }

    public String getTitle()
    {
        return this.title;
    }

    public boolean isAvailable() {
        return this.dispo == 1;
    }
}

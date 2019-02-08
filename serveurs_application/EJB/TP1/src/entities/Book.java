package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
        this.dispo = 1;
    }

    public void unborrow() {
        this.dispo = 0;
    }
}

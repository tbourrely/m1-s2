package TP2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "livre_emp")
public class Borrow implements Serializable {

    @Id
    private String isbn;

    @Column(name = "titre")
    private String title;

    @Column(name = "empruntepar")
    private int borrowerId;

    public Borrow()
    {}

    public Borrow(String isbn, String title, int borrower) {
        this.isbn = isbn;
        this.title = title;
        this.borrowerId = borrower;
    }

    public int getBorrowerId() {
        return borrowerId;
    }
}

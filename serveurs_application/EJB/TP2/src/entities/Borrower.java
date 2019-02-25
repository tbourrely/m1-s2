package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "emprunteur")
public class Borrower implements Serializable {

    @Id
    private int numemp;

    @Column(name = "nom")
    private String name;

    @Column(name = "nblivresemp")
    private int nbBorrowedBooks;

    public Borrower()
    {}

    public Borrower(int numemp, String name, int borrowedBooks)
    {
         this.numemp = numemp;
         this.name = name;
         this.nbBorrowedBooks = borrowedBooks;
    }

    public void setNbBorrowedBooks(int nb)
    {
        this.nbBorrowedBooks = nb;
    }

    public int getNbBorrowedBooks() {
        return nbBorrowedBooks;
    }

    public int getNumemp() {
        return numemp;
    }

    public String getName() {
        return name;
    }

    public void setNumemp(int numemp) {
        this.numemp = numemp;
    }

    public void setName(String name) {
        this.name = name;
    }
}

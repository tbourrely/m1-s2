package stateful;

import customExceptions.EmprunteurInvalide;
import customExceptions.LivreDejaEmprunte;
import customExceptions.NbMaxEmpruntsAtteint;

import javax.ejb.Remote;

@Remote
public interface BorrowManager {
    void createBorrow(int numemp) throws EmprunteurInvalide;
    void borrowBook(String isbn) throws NbMaxEmpruntsAtteint, LivreDejaEmprunte;
    void returnBook(String isbn);
}

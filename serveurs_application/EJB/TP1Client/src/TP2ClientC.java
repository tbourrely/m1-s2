import customExceptions.EmprunteurInvalide;
import customExceptions.LivreDejaEmprunte;
import customExceptions.NbMaxEmpruntsAtteint;
import stateful.BorrowManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TP2ClientC {
    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            BorrowManager bm = (BorrowManager)initialContext.lookup("stateful.BorrowManager");
//            bm.createBorrow(9); // EMPRUNTEUR INVALIDE

            // -- MAX NB DEJA EMPRUNTE --
            bm.createBorrow(1);
            bm.borrowBook("666");
            bm.borrowBook("555");
//            bm.borrowBook("444");
//            bm.borrowBook("333");
            /*
            UPDATE livre SET dispo=1 WHERE isbn IN ('666', '555', '444');
            DELETE FROM livre_emp WHERE isbn IN ('666', '555', '444');
            UPDATE emprunteur SET nblivresemp=0 WHERE numemp=1;
             */
            // --------------------------

            // -- LIVRE DEJA EMPRUNTE --
//            bm.createBorrow(1);
//            bm.borrowBook("666");
//            bm.createBorrow(2);
//            bm.borrowBook("666");
            /*
            UPDATE livre SET dispo=1 WHERE isbn='666';
            UPDATE emprunteur SET nblivresemp=0 WHERE numemp=1;
            DELETE FROM livre_emp WHERE isbn='666';
             */
            // -------------------------


        } catch (NamingException e) {
            e.printStackTrace();
        } catch (EmprunteurInvalide e) {
            System.out.println("Catched emprunteur invalide");
        } catch (NbMaxEmpruntsAtteint e) {
            System.out.println("Max nb deja emprunte");
        } catch (LivreDejaEmprunte e) {
            System.out.println("Livre deja emprunte");
        }
    }
}

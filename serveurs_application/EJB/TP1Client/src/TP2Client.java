import stateful.BorrowManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TP2Client {
    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            BorrowManager bm = (BorrowManager)initialContext.lookup("stateful.BorrowManager");
            bm.createBorrow(1);
            bm.borrowBook("666");
            bm.returnBook("666");
            bm.createBorrow(2);
            bm.borrowBook("666");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}

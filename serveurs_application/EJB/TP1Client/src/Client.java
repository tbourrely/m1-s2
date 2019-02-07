import stateful.SalutStateful;
import stateless.Salut;

import javax.naming.*;

public class Client {
    public static void main(String[] args) {
        try {
            InitialContext context = new InitialContext();
            Salut sb = (Salut)context.lookup("stateless.Salut");
            System.out.println(sb.salut("Thomas"));

            SalutStateful ss = (SalutStateful)context.lookup("stateful.SalutStateful");
            System.out.println(ss.salut("Pierre"));
            System.out.println(ss.resalut());

            context.close();
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }
}

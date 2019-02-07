package stateful;

import javax.ejb.Remote;

@Remote
public interface SalutStateful {
    String salut(String name);
    String resalut();
}

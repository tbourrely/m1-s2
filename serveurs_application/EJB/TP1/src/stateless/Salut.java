package stateless;

import javax.ejb.Remote;

@Remote
public interface Salut {
    String salut(String name);
}

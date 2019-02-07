package stateless;

import javax.ejb.Stateless;

@Stateless
public class SalutBean implements Salut {
    @Override
    public String salut(String name) {
        return "Hey " + name;
    }
}

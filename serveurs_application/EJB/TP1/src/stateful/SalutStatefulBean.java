package stateful;

import javax.ejb.Stateful;

@Stateful
public class SalutStatefulBean implements SalutStateful {

    private String name;

    @Override
    public String salut(String name) {
        this.name = name;
        return "Hey " + name;
    }

    @Override
    public String resalut() {
        return "Fetch from state : " + name;
    }
}

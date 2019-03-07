import javax.ejb.Stateless;

@Stateless
public class TraiteMessageBean implements TraiteMessage {

    public void traitement() {
        System.out.println("Traitement en cours");
    }
}

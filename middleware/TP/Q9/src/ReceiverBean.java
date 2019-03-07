import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
    name = "ReceiverEJB", 
    mappedName = "jms/TPDestination",
    activationConfig = {
        @ActivationConfigProperty(propertyName  = "messageSelector",
                                  propertyValue = "destinataire = 'MY_MDB'")
    }
)
public class ReceiverBean implements MessageListener {
    public ReceiverBean() {
    }

    @Override
    public void onMessage(Message message) {
        TextMessage tmsg = null;

        try {
            if (message instanceof TextMessage) {
                tmsg = (TextMessage)message;
                System.out.println(tmsg.getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

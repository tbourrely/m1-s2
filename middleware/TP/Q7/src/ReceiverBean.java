import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "ReceiverEJB", mappedName = "jms/TPDestination")
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

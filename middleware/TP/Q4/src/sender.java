import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class sender {
    public static void main(String[] args)
    {

        String message = args[0];
        String recipient = args.length != 2 ? null : args[1]; 

        try {
            InitialContext messaging = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory)
                    messaging.lookup("jms/TPConnectionFactory");
            Queue queue = (Queue) messaging.lookup("jms/TPDestination");
            QueueConnection connection = connectionFactory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            QueueSender queueSender = session.createSender(queue);

            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            
            if (recipient != null) {
               textMessage.setStringProperty("destinataire", recipient);
            } 

            queueSender.send(textMessage);

            System.out.println("message envoye");

            connection.close();
            System.exit(0);
        } catch(NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

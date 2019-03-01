import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Receiver {
    public static void main(String[] args)
    {

        try {
            InitialContext messaging = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory)
                    messaging.lookup("jms/TPConnectionFactory");
            Queue queue = (Queue) messaging.lookup("jms/TPDestination");
            QueueConnection connection = connectionFactory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();


            QueueReceiver queueReceiver = null;

            if (args.length == 0) {
              queueReceiver = session.createReceiver(queue);
            } else {
              String filter = "";
              for (int i = 0; i<args.length; i++) {
                filter += "destinataire='" + args[i] + "'";
                
                if ((i+1) < args.length) {
                  filter += " OR ";
                }
              }

              queueReceiver = session.createReceiver(queue, filter);
            }

            TextMessage textMessage = (TextMessage)queueReceiver.receive();

            System.out.println(textMessage.getText());

            connection.close();
            System.exit(0);

        } catch(NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }     
    }
}

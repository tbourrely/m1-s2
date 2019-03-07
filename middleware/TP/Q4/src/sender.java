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

            for(int i = 0; i<5; i++) {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(message+i);

                queueSender.send(textMessage);

                System.out.println("message envoye");
            }

            for(int i = 5; i<10; i++) {
                TextMessage textMessage = session.createTextMessage();
            
                if (recipient != null) {
                    System.out.println("Recipient : " + recipient);
                    textMessage.setStringProperty("destinataire", recipient);
                } 
                
                textMessage.setText(message+i);

                queueSender.send(textMessage);

                System.out.println("message envoye");
            }


            connection.close();
            System.exit(0);
        } catch(NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

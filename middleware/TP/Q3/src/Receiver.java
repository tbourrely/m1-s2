import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Receiver {
    public static void main(String[] args)
    {

        int sleepTime = Integer.parseInt(args[0]);
        String name = args[1];

        try {
            InitialContext messaging = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory)
                    messaging.lookup("jms/TPConnectionFactory");
            Queue queue = (Queue) messaging.lookup("jms/TPDestination");
            QueueConnection connection = connectionFactory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();

            QueueReceiver queueReceiver = session.createReceiver(queue);
            
            while(1==1) {
                TextMessage textMessage = (TextMessage)queueReceiver.receive();

            System.out.println(name);
            System.out.println(textMessage.getText());
            System.out.println("Sleeping for : " + Integer.toString(sleepTime));
            Thread.sleep(sleepTime);
            }

        } catch(NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

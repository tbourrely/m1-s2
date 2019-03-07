import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServletEmetteur extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String text = request.getParameter("text");

            InitialContext messaging = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory)
                    messaging.lookup("jms/TPConnectionFactory");
            Queue queue = (Queue) messaging.lookup("jms/TPDestination");
            QueueConnection connection = connectionFactory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            QueueSender queueSender = session.createSender(queue);

            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(text);
            textMessage.setStringProperty("destinataire", "MY_MDB");

            queueSender.send(textMessage);
            connection.close();

            PrintWriter out = response.getWriter();
            out.println("Message envoyé");
        } catch(NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
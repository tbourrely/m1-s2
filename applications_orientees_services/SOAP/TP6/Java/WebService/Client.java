import java.net.URL;
import javax.servlet.http.HttpServlet;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceRef;

import com.dataaccess.webservicesserver.*;

public class Client {

    @WebServiceRef(wsdlLocation = "https://www.dataaccess.com/webservicesserver/textcasing.wso?WSDL")
    public static TextCasing service = new TextCasing();

    public static void main(String[] args) {
        try {
            String name = "Test";
            TextCasingSoapType tcs = service.getTextCasingSoap();
            System.out.println(tcs.invertStringCase(name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
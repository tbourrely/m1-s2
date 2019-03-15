package tbo.soap.tp2;

import java.net.MalformedURLException;
import java.net.URL;
 
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import tbo.soap.tp2.HelloWorldInterface;

public class Client {
    public static void main(String[] args) {
       try {
        URL url = new URL("http://localhost:8080/SOAPTP2/HelloWorldService?wsdl");
        QName qname = new QName("http://tp2.soap.tbo/", "HelloWorldService");
        Service service = Service.create(url, qname);

        HelloWorldInterface hw = service.getPort(HelloWorldInterface.class);
        String name = "Thomas";

        System.out.println(hw.helloWorld(name));
       } catch (MalformedURLException e) {
           e.printStackTrace();
       }
    }
}
import tbo.soap.tp3.HelloWorldService;
import tbo.soap.tp3.HelloWorldService_Service;

public class Client {
    public static void main(String[] args) {
        HelloWorldService_Service service = new HelloWorldService_Service();
        HelloWorldService IBonjour = service.getHelloWorldServicePort();
        String name = "Thomas";
        
        System.out.println(IBonjour.helloWorld(name));
       
    }
}
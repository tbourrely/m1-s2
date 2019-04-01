import tbo.soap.tp5.HelloWorldService;
import tbo.soap.tp5.HelloWorldService_Service;

public class Client {
    public static void main(String[] args) {
        HelloWorldService_Service service = new HelloWorldService_Service();
        HelloWorldService IBonjour = service.getHelloWorldPort();
        String name = "Thomas";
        
        System.out.println(IBonjour.helloWorld(name));
       
    }
}
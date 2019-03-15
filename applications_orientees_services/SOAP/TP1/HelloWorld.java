package tbo.soap.tp1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import tbo.soap.tp1.HelloWorldInterface;

@WebService(endpointInterface = "tbo.soap.tp1.HelloWorldInterface")
public class HelloWorld implements HelloWorldInterface {
    @Override
    public String helloWorld(String name) {
        return "Hello World " + name;
    }
}
package tbo.soap.tp5;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import tbo.soap.tp5.HelloWorldInterface;

@WebService(endpointInterface = "tbo.soap.tp5.HelloWorldInterface")
public class HelloWorld implements HelloWorldInterface {
    @Override
    public String helloWorld(String name) {
        return "Hello World " + name;
    }
}
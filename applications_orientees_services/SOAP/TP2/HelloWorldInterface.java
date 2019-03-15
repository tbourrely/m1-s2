package tbo.soap.tp2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "HelloWorldService")
public interface HelloWorldInterface {
    @WebMethod
    @WebResult(name = "response")
    String helloWorld(
        @WebParam(name = "name")
        String name);
}
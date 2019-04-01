package sn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

// http://localhost:8080/hello_war_exploded/application.wadl

@Path("/hello")
public class HelloWorld {
    @GET
    @Produces("text/plain")
    public String salut() {
        return "Salut !";
    }
}

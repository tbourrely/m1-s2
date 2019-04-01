package sn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

// http://localhost:8080/hello_war_exploded/application.wadl

@Path("/")
public class HelloWorld {

    @GET
    @Path("/salut/{name}")
    @Produces("text/plain")
    public String salut(@PathParam("name") String name)
    {
        return "Salut " + name + "!";
    }
}

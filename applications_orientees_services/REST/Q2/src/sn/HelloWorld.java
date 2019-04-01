package sn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// http://localhost:8080/hello_war_exploded/application.wadl

@Path("/")
public class HelloWorld {

    @GET
    @Path("/salut")
    @Produces(MediaType.TEXT_PLAIN)
    public String salut()
    {
        return "Salut !";
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_XML)
    public String hello()
    {
        return "<?xml version='1.0' encoding='utf-8'?><message>Hello</message>";
    }

    @GET
    @Path("/ola")
    @Produces(MediaType.TEXT_HTML)
    public String ola()
    {
        return "Ola !";
    }
}

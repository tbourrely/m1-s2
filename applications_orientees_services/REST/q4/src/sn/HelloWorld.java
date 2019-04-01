package sn;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// http://localhost:8080/hello_war_exploded/application.wadl

@Path("/")
public class HelloWorld {

    @GET
    @Path("/salut")
    @Produces(MediaType.TEXT_PLAIN)
    public String salut(@QueryParam("nom") String name, @QueryParam("autreNom") String otherName)
    {
        return "Salut " + name + " ET " + otherName + "!";
    }

    @GET
    @Path("/salutsecured")
    @Produces(MediaType.TEXT_PLAIN)
    public String salutSecured(
            @DefaultValue("prenom")
            @QueryParam("nom")
            String name,
            @DefaultValue("autrePrenom")
            @QueryParam("autreNom")
            String otherName)
    {
        return "Salut " + name + " ET " + otherName + "!";
    }
}

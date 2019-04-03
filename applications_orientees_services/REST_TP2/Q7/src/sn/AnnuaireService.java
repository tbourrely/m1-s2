package sn;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class AnnuaireService {
    private final static Carnet carnet = new Carnet();

    @GET
    @Path("/carnet")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCarnetContent()
    {
        if (carnet.getContacts().size() == 0) {
            return "Liste vide";
        } else {
            String result = "";
            for (Contact c : carnet.getContacts()) {
                result += c.getNom() + " - " + c.getNumero() + "\n";
            }

            return result;
        }
    }

    @GET
    @Path("/carnet/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getNumberByName(@PathParam("name") String name)
    {
        for (Contact c : carnet.getContacts()) {
            if (c.getNom().equals(name)) {
                return c.getNumero();
            }
        }

        return "Inconnu";
    }

    @POST
    @Path("/contact")
    @Produces(MediaType.TEXT_PLAIN)
    public String createContact(@FormParam("nom") String nom, @FormParam("numero") String numero)
    {
        for (Contact c : carnet.getContacts()) {
            if (c.getNom().equals(nom)) {
                return "Contact deja existant";
            }
        }

        carnet.getContacts().add(new Contact(nom, numero));
        return "http://localhost:8080/annuaire/rest/carnet/" + nom;
    }

    @POST
    @Path("/contactObject")
    @Consumes(MediaType.TEXT_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String createContactByObject(Contact contact)
    {
        for (Contact c : carnet.getContacts()) {
            if (c.getNom().equals(contact.getNom())) {
                return "Contact deja existant";
            }
        }

        carnet.getContacts().add(contact);
        return "http://localhost:8080/annuaire/rest/carnet/" + contact.getNom();
    }

    @PUT
    @Path("/contactPut")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response editContact(Contact contact)
    {
        for (Contact c : carnet.getContacts()) {
            if (c.getNom().equals(contact.getNom())) {
                c.setNumero(contact.getNumero());
                return Response.ok().entity(c).build();
            }
        }

        return Response.noContent().build();
    }
}

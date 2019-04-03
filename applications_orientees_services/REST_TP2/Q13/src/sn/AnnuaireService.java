package sn;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Path("/")
public class AnnuaireService {

    private Carnet carnet;
    private String outputStreamDestination = "carnet.xml";

    public AnnuaireService()
    {
        try {
            JAXBContext jaxbContext   = JAXBContext.newInstance(Carnet.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File of = new File(outputStreamDestination);
            carnet = (Carnet)unmarshaller.unmarshal(of);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void save()
    {
        try {
            JAXBContext jaxbContext     = JAXBContext.newInstance(Carnet.class);
            File of = new File(outputStreamDestination);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(carnet, of);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

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
    public String getNumberByName(@PathParam("name") String name) throws ContactNotFoundException
    {
        for (Contact c : carnet.getContacts()) {
            if (c.getNom().equals(name)) {
                return c.getNumero();
            }
        }

        throw new ContactNotFoundException();
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
        save();
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
        save();
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
                save();
                return Response.ok().entity(c).build();
            }
        }

        return Response.noContent().build();
    }

    @DELETE
    @Path("/delete/{nom}/")
    public Response deleteContact(@PathParam("nom") String nom)
    {
        for (Contact c : carnet.getContacts()) {
            if (c.getNom().equals(nom)) {
                carnet.getContacts().remove(c);
                save();
                return Response.ok().build();
            }
        }

        return Response.notModified().build();
    }

    @GET
    @Path("/supp/{nom}")
    public Response deleteContactWithCookie(@PathParam("nom") String nom)
    {
        for (Contact c : carnet.getContacts()) {
            if (c.getNom().equals(nom)) {
                carnet.getContacts().remove(c);
                save();
                return Response.ok().cookie(new NewCookie("deletedName", c.getNom(), "/", "", "comment", 100, false)).build();
            }
        }

        return Response.notModified().build();
    }

    @GET
    @Path("/dernier")
    @Produces(MediaType.TEXT_PLAIN)
    public String showLastDeletedContact(@CookieParam("deletedName") String name)
    {
        return name;
    }

    @GET
    @Path("/listcookies")
    @Produces(MediaType.TEXT_PLAIN)
    public String showList(@Context HttpHeaders headers)
    {
        String result = "";

        for(String cName : headers.getCookies().keySet()) {
            result += cName + "\n";
        }

        return result;
    }
}

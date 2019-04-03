package sn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class AnnuaireService {
    private Carnet carnet = new Carnet();

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
}

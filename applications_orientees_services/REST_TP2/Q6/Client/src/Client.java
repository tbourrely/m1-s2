import org.glassfish.jersey.client.ClientConfig;
import sn.Contact;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Client {
    public static void main(String[] args) {
        ClientConfig cfg = new ClientConfig();
        javax.ws.rs.client.Client clt = ClientBuilder.newClient(cfg);

        WebTarget tgt = clt.target(getBaseURI());

        Contact contact = new Contact("Jean", "0701983");

        Response response = tgt.path("contactObject").request().accept(MediaType.TEXT_PLAIN).post(Entity.entity(contact, MediaType.TEXT_XML));
        String uri = response.readEntity(String.class);

        tgt = clt.target(uri);

        response = tgt.request().accept(MediaType.TEXT_PLAIN).get(Response.class);
        System.out.println(response.readEntity(String.class));
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/annuaire/rest").build();
    }
}

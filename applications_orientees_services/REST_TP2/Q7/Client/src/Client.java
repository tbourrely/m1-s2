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

        System.out.println("-- Will output : 204 --");
        Response response = tgt.path("contactPut").request().accept(MediaType.APPLICATION_XML).put(Entity.entity(contact, MediaType.APPLICATION_XML));
        System.out.println(response.getStatus());

        contact = new Contact("Jamy", "updated");
        response = tgt.path("carnet/Jamy").request().accept(MediaType.TEXT_PLAIN).get(Response.class);
        System.out.println("Jamy's number before PUT : " + response.readEntity(String.class));
        response = tgt.path("contactPut").request().accept(MediaType.APPLICATION_XML).put(Entity.entity(contact, MediaType.APPLICATION_XML));
        System.out.println(response.getStatus());
        response = tgt.path("carnet/Jamy").request().accept(MediaType.TEXT_PLAIN).get(Response.class);
        System.out.println("Jamy's number after PUT : " + response.readEntity(String.class));

    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/annuaire/rest").build();
    }
}

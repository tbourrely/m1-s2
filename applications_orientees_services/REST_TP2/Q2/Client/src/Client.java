import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.ClientBuilder;
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

        Response response = tgt.path("carnet").request().accept(MediaType.TEXT_PLAIN).get(Response.class);
        System.out.println("-- liste carnet --");
        System.out.println(response.readEntity(String.class));

        response = tgt.path("carnet").path("edouard").request().accept(MediaType.TEXT_PLAIN).get(Response.class);
        System.out.println("-- Contact invalide --");
        System.out.println(response.readEntity(String.class));

        response = tgt.path("carnet").path("Jamy").request().accept(MediaType.TEXT_PLAIN).get(Response.class);
        System.out.println("-- Contact valide --");
        System.out.println(response.readEntity(String.class));
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/annuaire").build();
    }
}

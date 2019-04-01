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

        Response response = tgt.path("hello").request().accept(MediaType.TEXT_PLAIN).get(Response.class);

        System.out.println(response.toString());
        System.out.println(response.readEntity(String.class));
        System.out.println(response.getStatus());
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/hello_war_exploded").build();
    }
}

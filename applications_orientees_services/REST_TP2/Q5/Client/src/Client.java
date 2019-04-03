import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Client {
    public static void main(String[] args) {
        ClientConfig cfg = new ClientConfig();
        javax.ws.rs.client.Client clt = ClientBuilder.newClient(cfg);

        WebTarget tgt = clt.target(getBaseURI());

        Form myForm = new Form();
        myForm.param("nom", "Jamy");
        myForm.param("numero", "07661379");

        System.out.println("-- Contact deja existant --");
        Response response = tgt.path("contact").request().accept(MediaType.TEXT_PLAIN).post(Entity.form(myForm));
        System.out.println(response.readEntity(String.class));

        myForm = new Form();
        myForm.param("nom", "Jean");
        myForm.param("numero", "07661379");

        System.out.println("-- Contact non existant --");
        response = tgt.path("contact").request().accept(MediaType.TEXT_PLAIN).post(Entity.form(myForm));
        String uri = response.readEntity(String.class);
        System.out.println(uri);

        tgt = clt.target(uri);
        response = tgt.request().accept(MediaType.TEXT_PLAIN).get(Response.class);
        System.out.println(response.readEntity(String.class));
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/annuaire/rest").build();
    }
}

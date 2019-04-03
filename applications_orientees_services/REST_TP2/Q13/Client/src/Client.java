import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

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

        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("User1", "user1");
        cfg.register(feature);

        javax.ws.rs.client.Client clt = ClientBuilder.newClient(cfg);

        WebTarget tgt = clt.target(getBaseURI());
        Response rAdd = tgt.path("contactObject").request().accept(MediaType.TEXT_PLAIN).post(Entity.entity(new Contact("test", "numTest"), MediaType.TEXT_XML));
        Response response = tgt.path("carnet").request().accept(MediaType.TEXT_PLAIN).get(Response.class);
        System.out.println("Liste : \n" + response.readEntity(String.class));

        Response rDelete = tgt.path("delete/test").request().accept(MediaType.TEXT_PLAIN).delete(Response.class);
        System.out.println("Delete status code : " + rDelete.getStatus());


        ClientConfig cfg2 = new ClientConfig();
        HttpAuthenticationFeature featureAdmin = HttpAuthenticationFeature.basic("Admin", "admin");
        cfg2.register(featureAdmin);

        javax.ws.rs.client.Client clt2 = ClientBuilder.newClient(cfg2);

        WebTarget tgt2 = clt2.target(getBaseURI());
        Response rDeleteAdmin = tgt2.path("delete/test").request().accept(MediaType.TEXT_PLAIN).delete(Response.class);
        System.out.println("Delete status code as admin : " + rDeleteAdmin.getStatus());
        Response rList = tgt2.path("carnet").request().accept(MediaType.TEXT_PLAIN).get(Response.class);
        System.out.println("Liste : \n" + rList.readEntity(String.class));
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/annuaire/rest").build();
    }
}

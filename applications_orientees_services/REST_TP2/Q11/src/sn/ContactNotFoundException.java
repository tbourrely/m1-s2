package sn;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ContactNotFoundException extends WebApplicationException {
    @Override
    public Response getResponse() {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Ce contact est inconnu").build();
    }
}

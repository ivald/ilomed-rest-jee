package exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by ivald79 on 31/08/2014.
 */
public class LoginException extends WebApplicationException {
    public LoginException(String message) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(message).type(MediaType.TEXT_PLAIN).build());
    }
}


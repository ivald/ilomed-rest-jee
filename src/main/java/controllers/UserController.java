package controllers;

import models.UserEntity;
import services.ifc.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.json.*;
import javax.ws.rs.*;

@Path("/users")
@Named
public class UserController {
    @Inject
    private UserService userService;

    @Path("/all")
    @GET()
    @Produces("application/json")
    public JsonArray getUsers() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        //for (UserEntity userEntity : userService.getAll()) {
            arrayBuilder.add(Json.createObjectBuilder().add("id", "userEntity.getId()"));
            arrayBuilder.add(Json.createObjectBuilder().add("email", "userEntity.getEmail()"));
        //}
        return arrayBuilder.build();
    }

    @Path("/exists/{email}")
    @GET()
    @Produces("application/json")
    public JsonObject exists(@PathParam("email") String email) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("exists", userService.find(email) != null);
        return builder.build();
    }

    @Path("/delete")
    @POST()
    public void deleteUser(@QueryParam("email") String email) {
        userService.delete(email);
    }
}

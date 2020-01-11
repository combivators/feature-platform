package net.tiny.feature.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.tiny.feature.demo.Users;

public interface UserService {
    @GET
    @Path("/v1/api/user/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Users.User getId(@PathParam("id")Integer id);
}

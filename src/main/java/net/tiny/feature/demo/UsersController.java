package net.tiny.feature.demo;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.tiny.service.ServiceContext;
import net.tiny.ws.rs.Roles;

@Path("/v1/api/user")
public class UsersController {

    protected static final Logger LOGGER = Logger.getLogger(UsersController.class.getName());

    @Resource
    private Users users;

    @Resource
    private ServiceContext serviceContext;

    @Roles(value = Roles.READER)
    @GET
    @Path("{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Users.User getId(@PathParam("id")Integer id) {
        LOGGER.info(String.format("call getId('%d') users: %d ServiceContext#%d",
                id, users.size(), serviceContext.hashCode()));
        return users.find(id);
    }
}

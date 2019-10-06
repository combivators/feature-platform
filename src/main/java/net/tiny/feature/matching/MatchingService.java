package net.tiny.feature.matching;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.tiny.feature.demo.Users;
import net.tiny.feature.model.Entry;
import net.tiny.feature.model.Settings;

//TODO This is a RESTful sample
@Path("/v1/api/match")
public class MatchingService {

    @Resource(name="users")
    private Users users;

    @GET
    @Path("query/{query}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Settings match(@PathParam("query")String query) {
        System.out.println("##### match query : " + query);
        Settings settings = new Settings();
        settings.entries = generator();
        return settings;
    }


    @POST
    @Path("edit/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Settings edit(@PathParam("id")String id, @BeanParam Entry entry) {
        System.out.println("##### edit id : " + id);
        Settings settings = new Settings();
        settings.entries = generator();
        return settings;
    }

    private List<Entry> generator() {
        EntryGenerator generator = new EntryGenerator(users);
        List<Entry> entries = new ArrayList<>();
        entries.add(generator.create());
        return entries;
    }
}

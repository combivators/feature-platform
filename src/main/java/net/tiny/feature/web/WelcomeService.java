package net.tiny.feature.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.tiny.ws.mvc.ModelAndView;

@Path("/v1/api/home")
public class WelcomeService {

    @GET
    @Path("index")
    @Produces(value = MediaType.APPLICATION_JSON)
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }

}

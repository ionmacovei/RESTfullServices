package md.utm.fi.proxy;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import md.utm.fi.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by imacovei on 1/5/2017.
 */
@Path("/resources")
public class ProxyController {


    @GET
    @Path(value = "/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUserName(@PathParam("username") String username) {

        User user = getConfigurationResurce(8085).path(username).get(User.class);
        System.out.println(user.toString());
        try {
            System.out.println("Request get by username");
            return Response.status(200).entity(user).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(404).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<User> users = getConfigurationResurce(8085).get(new GenericType<List<User>>() {});
        try {
            return Response.status(200).entity(users).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(404).build();
    }

    public WebResource getConfigurationResurce(Integer port) {
        ClientConfig cfg = new DefaultClientConfig();
        cfg.getClasses().add(JacksonJsonProvider.class);
        Client client = Client.create(cfg);
        WebResource apiRoot = client.resource("http://localhost:" + port+"/resources/");
        return apiRoot;
    }

}

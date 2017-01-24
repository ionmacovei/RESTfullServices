package md.utm.fi.proxy;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.istack.internal.NotNull;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import md.utm.fi.model.User;
//import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by imacovei on 1/5/2017.
 */
@Path("/users")
public class ProxyController {
    final static Logger logger = Logger.getAnonymousLogger();
    public String  keyPath= "http://localhost:8090/users";

    @GET
    @Path(value = "/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUserName(@PathParam("username") String username) {

        User user = getConfigurationResurce().path(username).get(User.class);
       // System.out.println(user.toString());
        try {
          //  System.out.println("Request get by username");
            return Response.status(200).entity(user).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(404).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<User> users = getConfigurationResurce().get(new GenericType<List<User>>() {
        });
        try {
            return Response.status(200).entity(users).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(404).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response saveUser(User user) {
        ClientResponse response = getConfigurationResurce().post(ClientResponse.class, user);
        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        return Response.status(201).build();

    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteUser(@PathParam("id") Integer id) {
        getConfigurationResurce().path(id.toString()).delete();
        return Response.status(200).build();
    }

    public WebResource getConfigurationResurce() {
        ClientConfig cfg = new DefaultClientConfig();
        cfg.getClasses().add(JacksonJsonProvider.class);
        Client client = Client.create(cfg);
        WebResource apiRoot = client.resource("http://localhost:" + getDWport() + "/users/");
        return apiRoot;
    }

    public static Integer getDWport() {
        Integer port=  new Random().nextInt(2) + 8085;
        logger.info("Request to DataWarehouse with port: "+port);
        return port;
    }

}

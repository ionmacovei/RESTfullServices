package md.utm.fi.datawarehause;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.utm.fi.model.User;
import md.utm.fi.sincronizeServices.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static md.utm.fi.sincronizeServices.SerializationServices.*;

/**
 * Created by imacovei on 12.12.2016.
 */

@Path("/resources")
public class UserController {
    UserDAO userDAO = new UserDAO();

/*
     @GET
    @Path(value = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUserName(@PathParam("id") String id) {

        try {
            System.out.println("Request get by username");
            return Response.status(200).entity(userDAO.getEmployeeByID(id)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(404).build();
    }*/

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        try {
            return Response.status(200).entity(userDAO.getFromDB("users")).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(404).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response saveUser(User emp) {

        System.out.println();
        String result = "User saved : " + emp;
        userDAO.insertToDB(emp);
        System.out.println(emp.toString());
        return Response.status(201).build();

    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteUser(@PathParam("id") Integer id) {
        userDAO.deleteEmploye(id);
        return Response.status(201).build();
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUsername(@PathParam("username") String userName) {
        try {
            System.out.println("Request get by username");
            return Response.status(200).entity(userDAO.getEmployeeByUsername(userName)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(404).build();
    }
}

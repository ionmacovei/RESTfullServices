package md.utm.fi.datawarehause;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertFtoC() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String result=mapper.writeValueAsString(serializeObjects(userDAO.getFromDB("users")));
            return Response.status(200).entity(result).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Response.status(404).build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(String emp) {

        String result = "User saved : " + emp;
        userDAO.insertToDB(deserializeObjects(emp));
        System.out.println(emp.toString());
        System.out.println(result);

        return Response.status(201).entity(result).build();

    }
    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteEmployee(@PathParam("id") String firtstName ){
        userDAO.deleteEmploye(firtstName);
        return Response.status(201).build();
    }
    @OPTIONS
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByID(@PathParam("id") String id ) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String result =mapper.writeValueAsString(serializeObjects(userDAO.getEmployeeByID(id)));
            return Response.status(200).entity(result).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Response.status(404).build();
    }



}

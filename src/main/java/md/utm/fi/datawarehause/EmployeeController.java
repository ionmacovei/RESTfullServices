package md.utm.fi.datawarehause;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.utm.fi.model.Employee;
import md.utm.fi.sincronizeServices.*;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static md.utm.fi.sincronizeServices.SerializationServices.*;

/**
 * Created by imacovei on 12.12.2016.
 */

@Path("/resources")
public class EmployeeController {
  EmployeeDAO employeeDAO = new EmployeeDAO();
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertFtoC() {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name", "ion");
        jsonObject.put("salary", 10000);

        String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
        try {
            return Response.status(200).entity(mapper.writeValueAsString(serializeObjects(employeeDAO.getFromDB("employees")))).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Response.status(404).build();
    }

    @PUT
    @Path("/put")
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(String emp) {

        String result = "Employee saved : " + emp;
        deserializeObjects(emp);
        System.out.println(emp.toString());
        System.out.println(result);

        return Response.status(201).entity(result).build();

    }

}

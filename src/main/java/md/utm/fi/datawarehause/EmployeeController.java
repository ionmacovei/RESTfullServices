package md.utm.fi.datawarehause;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by imacovei on 12.12.2016.
 */

@Path("/resources")
public class EmployeeController {

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
            return Response.status(200).entity(mapper.writeValueAsString(jsonObject)).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Response.status(404).build();
    }

}

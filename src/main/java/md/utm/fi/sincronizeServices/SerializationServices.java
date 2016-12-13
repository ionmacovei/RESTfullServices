package md.utm.fi.sincronizeServices;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.utm.fi.model.Employee;

import java.io.IOException;

/**
 * Created by imacovei on 12/13/2016.
 */
public class SerializationServices {
    public  static String serializeObjects(Object objToString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writeValueAsString(objToString);
            return jsonInString;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Employee deserializeObjects(String employeeInString) {
        ObjectMapper  mapper = new ObjectMapper();

        Employee employee = null;
        try {
            employee = mapper.readValue(employeeInString,Employee.class);
            return employee;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

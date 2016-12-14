package md.utm.fi.sincronizeServices;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.utm.fi.model.User;


import java.io.IOException;

/**
 * Created by imacovei on 12/13/2016.
 */
public class SerializationServices {
    public  static String serializeObjects(Object objToString) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
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
    public static User deserializeObjects(String employeeInString) {
        ObjectMapper  mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        User user = null;
        try {
            user = mapper.readValue(employeeInString,User.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package md.utm.fi.sincronizeServices;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import md.utm.fi.model.User;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public static User deserializeUser(String employeeInString) {
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
    public static ArrayList<User> deserializeUsersList(String employeeListInString) {
        ObjectMapper  mapper = new ObjectMapper();
        try {
            TypeReference<List<User>> mapType = new TypeReference<List<User>>() {
            };
            ArrayList<User> jsonToEmployeeList = mapper.readValue(employeeListInString, mapType);
            return jsonToEmployeeList;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package md.utm.fi.sincronizeServices;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;
import md.utm.fi.model.Employee;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.MongoClient;
import org.mongojack.*;
import org.mongojack.WriteResult;


import static md.utm.fi.sincronizeServices.SerializationServices.*;

/**
 * Created by imacovei on 12/13/2016.
 */
public class EmployeeDAO {
    private static MongoClient mongo;

    public static DB getConnection() {
        DB db = null;
        try {
            mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("employee");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return db;
    }

    public void insertToDB(Employee employee) {

        JacksonDBCollection<Employee, String> coll = JacksonDBCollection.wrap(getConnection().getCollection("employees"), Employee.class, String.class);
        coll.insert(employee);
        System.out.println("Document inserted successfully");
    }


    public ArrayList<Employee> getFromDB(String collName) {
        ArrayList<Employee> employeesList = new ArrayList<Employee>();
        JacksonDBCollection<Employee, String> collection = JacksonDBCollection.wrap(getConnection().getCollection("employees"), Employee.class, String.class);
        while (collection.find().hasNext()) {
            Employee emp = collection.find().next();
            employeesList.add(emp);
        }
        return employeesList;
    }

    public boolean deleteEmploye(String firstName) {
        DBCollection collection = getConnection().getCollection("employees");
        BasicDBObject document = new BasicDBObject();
        document.put("firstName", firstName);
        collection.remove(document);
        return false;
    }

    public Employee getEmployeeByID(String Id) {
        JacksonDBCollection<Employee, String> coll = JacksonDBCollection.wrap(getConnection().getCollection("employees"), Employee.class, String.class);
        DBCollection collection = getConnection().getCollection("employees");
        BasicDBObject document = new BasicDBObject();
        document.put("id", Id);
        DBObject empob = collection.findOne(document);
        String id=(String) empob.get("id");
        String firstName=(String) empob.get("firstName");
        String lastName=(String) empob.get("lastName");
        String department=(String) empob.get("department");
        Integer salary =(Integer) empob.get("salary");
        Employee employee= new Employee(id,firstName,lastName,department,salary);
        return employee;
    }

    public boolean deleteAllEmployees() {
        DBCollection collection = getConnection().getCollection("employees");
        collection.drop();
        return true;
    }

}

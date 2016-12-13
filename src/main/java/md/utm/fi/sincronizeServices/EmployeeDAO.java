package md.utm.fi.sincronizeServices;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.util.JSON;
import md.utm.fi.model.Employee;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import com.mongodb.MongoClient;


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

    public void insertToDB(String collname, Employee employee) {

        DBCollection coll = getConnection().createCollection(collname, null);
        Employee emp = new Employee("jaki", "chean", "comedy", 1000);
        String json = serializeObjects(emp);
        DBObject dbObject = (DBObject) JSON.parse(json);
        coll.insert(dbObject);
        System.out.println("Document inserted successfully");
    }


    public ArrayList<Employee> getFromDB(String collName) {
        ArrayList<Employee> employeesList = new ArrayList<Employee>();
        DBCollection coll = getConnection().getCollection(collName);
        DBCursor cursor = coll.find();
        while (cursor.hasNext()) {
            DBObject dbObj = cursor.next();
            String firstName = (String) dbObj.get("firstName");
            String lastName = (String) dbObj.get("lastName");
            String department = (String) dbObj.get("department");
            Integer salary = (Integer) dbObj.get("salary");
            Employee emp = new Employee(firstName, lastName, department, salary);
            employeesList.add(emp);
        }
        String json = serializeObjects(employeesList);
       // System.out.println(json);
        return employeesList;
    }

    public boolean deleteEmploye(String firstName){
        DBCollection collection = getConnection().getCollection("employee");
        BasicDBObject document = new BasicDBObject();
        document.put("firstName", firstName);
        collection.remove(document);
       return  false;
    }
}

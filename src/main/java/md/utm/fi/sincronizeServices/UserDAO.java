package md.utm.fi.sincronizeServices;

import com.mongodb.*;
import md.utm.fi.model.User;

import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.MongoClient;
import org.mongojack.JacksonDBCollection;
//import org.mongojack.*;

/**
 * Created by imacovei on 12/13/2016.
 */
public class UserDAO {
    private static MongoClient mongo;

    public static DB getConnection() {
        DB db = null;
        try {
            mongo = new MongoClient("localhost", 27017);
            db = mongo.getDB("user");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return db;
    }

    public void insertToDB(User user) {

        JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(getConnection().getCollection("users"), User.class, String.class);
        user.setId(getMaxId()+1);
        coll.insert(user);
        System.out.println("Document inserted successfully");
    }


    public ArrayList<User> getFromDB(String collName) {

        ArrayList<User> usersList = new ArrayList<User>();
        DBCollection coll = getConnection().getCollection("users");
        DBCursor cursor = coll.find();
        while (cursor.hasNext()) {
            DBObject dbObj = cursor.next();
            Integer id=(Integer) dbObj.get("id");
            String firstName = (String) dbObj.get("firstName");
            String lastName = (String) dbObj.get("lastName");
            String username = (String) dbObj.get("username");
            String password = (String) dbObj.get("password");
            User emp = new User(id,firstName, lastName, username, password);
            usersList.add(emp);
        }
        return usersList;
    }

    public boolean deleteEmploye(String id) {
        DBCollection collection = getConnection().getCollection("users");
        BasicDBObject document = new BasicDBObject();
        document.put("id", id);
        collection.remove(document);
        return false;
    }

    public User getEmployeeByID(String Id) {
        //JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(getConnection().getCollection("users"), User.class, String.class);
        DBCollection collection = getConnection().getCollection("users");
        BasicDBObject document = new BasicDBObject();
        document.put("id", Id);
        DBObject empob = collection.findOne(document);
        Integer id=(Integer) empob.get("id");
        String firstName=(String) empob.get("firstName");
        String lastName=(String) empob.get("lastName");
        String username=(String) empob.get("username");
        String password =(String) empob.get("password");
        User user = new User(id,firstName,lastName,username,password);
        return user;
    }

    public boolean deleteAllEmployees() {
        DBCollection collection = getConnection().getCollection("users");
        collection.drop();
        return true;
    }
    public Integer getMaxId(){
        DBObject sort = new BasicDBObject();
        Integer id=0;
        sort.put("id", -1);
       DBCursor cursor=getConnection().getCollection("users").find().sort(sort).limit(1);
        while (cursor.hasNext()) {

            id = (Integer) (cursor.next().get("id"));
        }
        return id;

    }

}

package md.utm.fi.datawarehause;

import md.utm.fi.model.User;
import md.utm.fi.sincronizeServices.UserDAO;

/**
 * Created by imacovei on 12.12.2016.
 */
public class StartDW {
    public static void main(String[] args){
        UserDAO dao = new UserDAO();


        //User emp = new User("emp4","jet", "cheanisie", "liee", "emp4");
        //dao.insertToDB(emp);
      // System.out.println(dao.getFromDB("users"));
        //dao.deleteAllEmployees();
        new JerseyServer(8085).run();
        //  dao.getMaxId();
    }
}

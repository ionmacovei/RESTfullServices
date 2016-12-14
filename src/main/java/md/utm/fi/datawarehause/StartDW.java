package md.utm.fi.datawarehause;

import md.utm.fi.sincronizeServices.UserDAO;

/**
 * Created by imacovei on 12.12.2016.
 */
public class StartDW {
    public static void main(String[] args){
        UserDAO dao = new UserDAO();

        //User emp = new User("jet", "cheanisie", "comedy", 1000);
       // dao.insertToDB("employees",emp);
       // dao.getFromDB("employees");
        //dao.deleteAllEmployees();
        new JerseyServer(8085).run();
    }
}

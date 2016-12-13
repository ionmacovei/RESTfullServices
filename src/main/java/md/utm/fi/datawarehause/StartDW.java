package md.utm.fi.datawarehause;

import md.utm.fi.model.Employee;
import md.utm.fi.sincronizeServices.EmployeeDAO;

/**
 * Created by imacovei on 12.12.2016.
 */
public class StartDW {
    public static void main(String[] args){
        EmployeeDAO dao = new EmployeeDAO();

        //Employee emp = new Employee("jet", "cheanisie", "comedy", 1000);
       // dao.insertToDB("employees",emp);
       // dao.getFromDB("employees");
        new JerseyServer(8085).run();

    }
}

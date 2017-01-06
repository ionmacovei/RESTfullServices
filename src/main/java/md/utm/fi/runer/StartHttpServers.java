package md.utm.fi.runer;

import md.utm.fi.datawarehause.JerseyServer;
import md.utm.fi.model.User;
import md.utm.fi.proxy.ProxyController;
import md.utm.fi.sincronizeServices.UserDAO;

/**
 * Created by imacovei on 12.12.2016.
 */
public class StartHttpServers {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();


        //User emp = new User("emp4","jet", "cheanisie", "liee", "emp4");
        //dao.insertToDB(emp);
        // dao.deleteEmploye(3);
       // System.out.println(ProxyController.getDWport());

        //Start DataWarehouses
        new JerseyServer(8085, "md.utm.fi.datawarehause").run();
        new JerseyServer(8086, "md.utm.fi.datawarehause").run();
        //Start ProxyServer
        new JerseyServer(8090, "md.utm.fi.proxy").run();

    }
}

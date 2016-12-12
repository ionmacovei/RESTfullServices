package md.utm.fi.datawarehause;

/**
 * Created by imacovei on 12.12.2016.
 */
public class StartDW {
    public static void main(String[] args){

        new JerseyServer(8085).run();
    }
}

package md.utm.fi.datawarehause;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
//import com.sun.jersey.json.impl.provider.entity.JSONArrayProvider;
import com.sun.net.httpserver.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

/**
 * Created by imacovei on 12.12.2016.
 */
public class JerseyServer implements Runnable {

    private static Integer port;
    private static String  packegeConfig;
    public JerseyServer(Integer port, String packegeConfig) {
        this.port = port;
        this.packegeConfig=packegeConfig;
    }

    private static HttpServer createHttpServer() throws IOException {
        ResourceConfig config = new PackagesResourceConfig(packegeConfig);

        return HttpServerFactory.create(getURI(), config);
    }

    private static URI getURI() {
        return UriBuilder.fromUri("http://localhost/").port(port).build();
    }

    public void run() {

        System.out.println("Starting  Jersey HTTPServer...\n");
        HttpServer jerseyServer = null;
        try {
            jerseyServer = createHttpServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jerseyServer.start();
        System.out.println(String.format("\nJersey Application Server started at " + getURI()));

    }
}

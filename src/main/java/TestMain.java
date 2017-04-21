import com.sun.xml.internal.ws.api.policy.PolicyResolver;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Server server=new Server(80);
        ServletContextHandler h=new ServletContextHandler();
        h.addServlet(TestServlet.class, "/");
        server.setHandler(h);
        server.start();
    }
}

package rest;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * Created by home on 28.04.14.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class TestSessionService {
    @Context
    private HttpServletRequest httpRequest;
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        HttpSession httpSession = httpRequest.getSession();
        if( httpSession.getAttribute("id") == null){
            httpSession.setAttribute("id", new Integer(1));
        }else{
            Integer i = (Integer)httpSession.getAttribute("id");
            httpSession.setAttribute("id", new Integer(i+1));
        }
        Integer i2 = (Integer)httpSession.getAttribute("id");
        String st = String.valueOf(i2);
        return st;
    }
}

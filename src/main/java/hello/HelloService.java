package hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by user on 28/7/15.
 */

@Path("hello")
public class HelloService {

    @GET
    public String getHello() {
        return "Hello EE";
    }
}

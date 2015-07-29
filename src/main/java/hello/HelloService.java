package hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Yashawant and Tanmayee on 28/7/15.
 */

@Path("/")
public class HelloService {
    private final MongoDBService mongoDBService = new MongoDBService("localhost",27017);

    @GET
    @Path("hello")
    @Produces("text/json")
    public String getHello() {
        return "Hello EE";
    }


    @GET
    @Path("testdb")
    public String testDB(){
        mongoDBService.testDb("shoppingCart","cart");
        return "Done";
    }

}

package hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created by Yashawant and Tanmayee on 28/7/15.
 */

@Path("/")
public class HelloService {
    private final MongoDBService mongoDBService = new MongoDBService("localhost",27017);

    @GET
    @Path("hello")
    @Produces("text/json")
    public String getHello(@QueryParam("cartName") String cartName) {
        StringBuilder result= new StringBuilder(cartName);
        result.append(" ");
        boolean isCartExist= mongoDBService.isCartExist("shoppingCart","cart",cartName);
        if(isCartExist)
            result.append("Available");
        else
        result.append("Not Available");
        return result.toString();
    }
}

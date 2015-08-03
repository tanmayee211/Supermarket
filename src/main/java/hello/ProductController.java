package hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/")
public class ProductController {

    private  MongoDBService mongoDBService =null;

    public void setMongoDBService(MongoDBService mongoDBService) {
        this.mongoDBService = mongoDBService;
    }
    public MongoDBService getMongoDBService() {
        return mongoDBService;
    }

    @GET
    @Path("products")
    @Produces("text/json")
    public String getProductList() {


        return null;

    }


}

package controller;

import Service.MongoDBService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class ProductController {

    private MongoDBService mongoDBService = null;

    public void setMongoDBService(MongoDBService mongoDBService) {
        this.mongoDBService = mongoDBService;
    }
    public MongoDBService getMongoDBService() {
        return mongoDBService;
    }

    @GET
    @Path("products")
    @Produces("text/json")
    public List getProductList() {
        List productList = new ArrayList<>();
        return productList;
    }
}

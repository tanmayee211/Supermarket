package resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.ProductDao;
import domain.Product;
import org.bson.types.ObjectId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Singleton
public class ProductResource {

    private String ADD_PRODUCT_SUCCESS_MESSAGE="Successfully added!";
    private String ADD_PRODUCT_FAILURE_MESSAGE ="Adding failed!";
    private ProductDao productDao;

    @Inject
    public ProductResource(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GET
    @Path("products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {

        return Response.status(Response.Status.OK).entity(productDao.getAll()).build();
    }

    @POST
    @Path("product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postProduct(Product product) {
        ObjectId objectId = productDao.addProduct(product);
        if(objectId!=null) {
            return Response.status(Response.Status.CREATED).entity(ADD_PRODUCT_SUCCESS_MESSAGE).build();
        }
        return Response.status(Response.Status.CREATED).entity(ADD_PRODUCT_FAILURE_MESSAGE).build();
    }
}

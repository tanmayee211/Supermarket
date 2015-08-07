package resource;

import dao.ProductDao;
import domain.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class ProductResource {
    private ProductDao productDao;

    public ProductResource(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductResource(){}

    @GET
    @Path("products")
    @Produces(MediaType.APPLICATION_JSON)
    public List getProducts() {
       return productDao.getAll();
    }

    @POST
    @Path("product")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postProduct(Product product) {

      return   Response.status(Response.Status.CREATED).entity(productDao.addProduct(product)).build();
    }


}

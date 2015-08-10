package resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.ProductDao;
import domain.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
@Singleton
public class ProductResource {
    private ProductDao productDao;

    @Inject
    public ProductResource(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GET
    @Path("products")
    @Produces(MediaType.APPLICATION_JSON)
    public List getProducts() {
       return productDao.getAll();
    }

    @POST
    @Path("product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postProduct(Product product) {
      return   Response.status(Response.Status.CREATED).entity(productDao.addProduct(product)).build();
    }


}

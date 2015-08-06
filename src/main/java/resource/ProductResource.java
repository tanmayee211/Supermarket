package resource;

import dao.ProductDao;
import dto.Product;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/")
public class ProductResource {



    private ProductDao productDao;

    public ProductResource(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductResource(){}

    @GET
    @Path("product")
    @Produces(MediaType.APPLICATION_JSON)
    public List getProducts() {
       return productDao.getAll();
    }
}

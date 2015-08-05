package resource;

import dao.ProductDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/")
public class ProductResource {

    private ProductDao productDao;

    public ProductResource(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GET
    @Path("product")
    @Produces("text/json")
    public List getProducts() {
        return productDao.getAll();
    }
}

package junit.controller;
import controller.ProductController;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;

/**
 * Created by Yashawant and Tanmayee on 28/7/15.
 */
public class ProductControllerSpec {
    @Test
    public void it_shouldReturnProductList(){
        ProductController productController = new ProductController();
        List productList = productController.getProductList();
        Assert.assertThat(productList.isEmpty(), is(false));
    }
}

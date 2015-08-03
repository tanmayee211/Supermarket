import hello.ProductController;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yashawant and Tanmayee on 28/7/15.
 */
public class ProductControllerSpec {
    @Test
    public void it_shouldReturnHello(){
        ProductController productController = new ProductController();
        String actual = productController.getProductList();
       // Assert.assertEquals("cartName1 Not Available", actual);
    }
}

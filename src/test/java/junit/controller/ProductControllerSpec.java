package junit.controller;
import Service.MongoDBService;
import dto.Product;
import controller.ProductController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * Created by Yashawant and Tanmayee on 28/7/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerSpec {
    @Test
    public void it_shouldReturnProductList(){

        ProductController productController = new ProductController();
        MongoDBService mongoDBServiceMock = Mockito.mock(MongoDBService.class);
        productController.setMongoDBService(mongoDBServiceMock);
        Product doveSoap=new Product();
        doveSoap.setId(1);
        doveSoap.setName("Dove Soap");
        doveSoap.setPrice(35);
        when(mongoDBServiceMock.getProductList()).thenReturn(Arrays.asList(doveSoap));

        List productList = productController.getProductList();

        Assert.assertThat(productList.isEmpty(), is(false));
    }
}

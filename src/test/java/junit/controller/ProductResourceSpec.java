package junit.controller;

import dao.ProductDao;
import dto.Product;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import resource.ProductResource;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Yashawant and Tanmayee on 28/7/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductResourceSpec {
    @Test
    public void itShouldPostAProduct(){
        ProductDao productDao = Mockito.mock(ProductDao.class);
        ProductResource productResource = new ProductResource(productDao);
        Product laptop = createProduct("Lenovo Thinkpad T420", 40000.00);
        when(productDao.addProduct(laptop)).thenReturn(ObjectId.get());

        Response response=productResource.postProduct(laptop);

        verify(productDao).addProduct(laptop);
        assertThat(response.getStatus(), is(Response.Status.CREATED.getStatusCode()));
        assertThat(response.getEntity(),notNullValue());

    }

    @Test
    public void itShouldGetAllProducts() {
        ProductDao productDao = Mockito.mock(ProductDao.class);
        ProductResource productResource = new ProductResource(productDao);
        Product doveSoap = createProduct("dove soap", 22.0);
        Product tea = createProduct("tetley", 33);
        Product coffee = createProduct("bru", 11.0);
        when(productDao.getAll()).thenReturn(Arrays.asList(doveSoap, tea, coffee));

        List products = productResource.getProducts();

        verify(productDao).getAll();
        assertThat(products.contains(doveSoap), is(true));
        assertThat(products.contains(tea), is(true));
        assertThat(products.contains(coffee), is(true));
    }

    public Product createProduct(String description, double unitPrice) {
        Product product = new Product(description, unitPrice);
        return product;
    }
}

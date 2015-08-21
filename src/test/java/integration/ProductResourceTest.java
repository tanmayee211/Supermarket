package integration;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.ServletModule;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;
import common.MongoUtility;
import dao.MorphiaConfig;
import dao.ProductDao;
import org.bson.Document;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ProductResourceTest{
    private static final String PRODUCT = "product";
    private final URI uri;
    private static final String DATABASE = "supermarket";
    private HttpServer httpServer;
    Client restClient;
    private static final int PORT = 27017;
    private static final String HOST = "localhost";

    public ProductResourceTest() {
         uri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        restClient=Client.create(new DefaultClientConfig());
    }

    @Before
    public void startServer() throws IOException {

        ResourceConfig resourceConfig = new PackagesResourceConfig("resource");
        Injector injector = Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                bind(ProductDao.class);
                bind(MorphiaConfig.class);
                HashMap<String, Object> options = new HashMap<>();
                options.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
                resourceConfig.setPropertiesAndFeatures(options);
            }
        });

        IoCComponentProviderFactory guiceComponentProviderFactory = new GuiceComponentProviderFactory(resourceConfig, injector);
        httpServer = GrizzlyServerFactory.createHttpServer(uri + "rest/", resourceConfig, guiceComponentProviderFactory);
    }

    @After
    public void stopServer() {
        httpServer.stop();
    }

    @Test
    public void itShouldGetAllProducts(){

        MongoClient mongoClient = new MongoClient(HOST,PORT);
        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<Document> collection = database.getCollection(PRODUCT);
        Document doveSoapJson = getJSONDocument("{'name':'dove soap', 'price':22.2}");
        Document teaJson = getJSONDocument("{'name':'tea', 'price':12.3}");
        Document coffeeJson = getJSONDocument("{'name':'Coffee', 'price':12.5}");
        collection.insertMany(Arrays.asList(doveSoapJson, teaJson, coffeeJson));

        WebResource restService = restClient.resource(uri);
        ClientResponse response = restService.path("rest").path("products")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        String actual = response.getEntity(String.class);
        String expected="[{\"price\":22.2,\"name\":\"dove soap\"},{\"price\":12.3,\"name\":\"tea\"},{\"price\":12.5,\"name\":\"Coffee\"}]";

        assertThat(response.getStatus(), equalTo(200));
        assertThat(actual.contains(expected), is(true));
    }

    @Test
    public void itShouldAddAProduct(){
        String tropicanaProduct = "{\"price\":22.2,\"name\":\"Tropicana Orange\"}";

        WebResource restService = restClient.resource(uri);
        ClientResponse response= restService.path("rest").path("product")
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class,tropicanaProduct);
        String actual = response.getEntity(String.class);

        assertThat(response.getStatus(), equalTo(201));
        assertThat(actual, equalTo("Successfully added!"));

    }

    private Document getJSONDocument(String json) { return Document.parse(json);
    }
}

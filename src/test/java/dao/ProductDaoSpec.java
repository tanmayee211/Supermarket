package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.h2.H2Backend;
import domain.Product;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductDaoSpec {
    public static final String PRODUCT = "product";
    private MongoServer server;
    private MongoClient mongoClientForTest;
    private Properties properties;
    private String databaseName;
    public static final String H2_BACKEND_FILE = "supermarket.mv";

    @Before
    public void setUp() {

        server = new MongoServer(new H2Backend(H2_BACKEND_FILE));
        properties  = new Properties();
        String propFileName = "appConfig.properties";
        InputStream propertiesStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        try {
            properties.load(propertiesStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String host = properties.getProperty("mongo.host");
        Integer port = Integer.parseInt(properties.getProperty("mongo.port"));
        databaseName=properties.getProperty("mongo.databaseName");
        server.bind(host,port);
        mongoClientForTest = new MongoClient(host, port);
    }

    @After
    public void tearDown() {
        mongoClientForTest.close();
        server.shutdown();
        File h2Backendfile = new File(H2_BACKEND_FILE);
        h2Backendfile.delete();
    }


    @Test
    public void itShouldAddAProduct() {

        ProductDao productDao = new ProductDao(new MorphiaConfig());
        Product laptop = createProduct("Lenovo Thinkpad T420", 40000.00);

        ObjectId laptopId = productDao.addProduct(laptop);

        MongoDatabase database = mongoClientForTest.getDatabase(databaseName);
        MongoCollection<Document> products = database.getCollection(PRODUCT);
        BasicDBObject findQuery = new BasicDBObject();
        String productId = "_id";
        findQuery.put(productId, laptopId);
        MongoCursor<Document> cursor = products.find(findQuery).iterator();

        assertThat("should return a non-empty Curser", cursor.hasNext(), is(true));
        Document laptopDocument = cursor.next();
        assertThat("should match product name", laptopDocument.get("name").toString(), equalTo(laptop.getName()));
        assertThat("should match product price", (Double) laptopDocument.get("price"), equalTo(laptop.getPrice()));
        assertThat("should match product productId", (ObjectId) laptopDocument.get(productId), equalTo(laptop.getProductId()));
    }


    @Test
    public void itShouldGetAllProducts() {

        MongoDatabase database = mongoClientForTest.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(PRODUCT);
        Document doveSoapJson = getJSONDocument("{'name':'dove soap', 'price':22.2}");
        Document teaJson = getJSONDocument("{'name':'tea', 'price':12.3}");
        Document coffeeJson = getJSONDocument("{'name':'Coffee', 'price':12.5}");
        collection.insertMany(Arrays.asList(doveSoapJson, teaJson, coffeeJson));

        Product doveSoap = createProduct("dove soap", 22.2);
        Product tea = createProduct("tea", 12.3);
        Product coffee = createProduct("Coffee", 12.5);
        ProductDao dao = new ProductDao(new MorphiaConfig());

        List products = dao.getAll();

        assertThat(products.contains(doveSoap), is(true));
        assertThat(products.contains(tea), is(true));
        assertThat(products.contains(coffee), is(true));
    }

    public Document getJSONDocument(String json) {
        return Document.parse(json);
    }

    public Product createProduct(String name, double price) {
        return new Product(name, price);
    }

}
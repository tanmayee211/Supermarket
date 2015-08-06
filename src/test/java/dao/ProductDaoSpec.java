package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import dto.Product;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;


import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by user on 5/8/15.
 */
public class ProductDaoSpec {
    public static final String DATABASE_NAME = "testdb";
    public static final String PRODUCT = "product";

    private MongoServer server;
    private MongoClient mongoClient;
    private MongoClient mongoClientForTest;

    @Before
    public void setUp() {
        server = new MongoServer(new MemoryBackend());
        InetSocketAddress inetSocketAddress = server.bind();
        ServerAddress serverAddress = new ServerAddress(inetSocketAddress);
        mongoClient = new MongoClient(serverAddress);
        mongoClientForTest = new MongoClient(serverAddress);
    }

    @After
    public void tearDown() {
        mongoClient.close();
        mongoClientForTest.close();
        server.shutdown();
    }


    @Test
    public void itShouldAddAProduct() {

        ProductDao productDao = new ProductDao(mongoClient, DATABASE_NAME);
        Product laptop = createProduct("Lenovo Thinkpad T420", 40000.00);

        ObjectId laptopId = productDao.addProduct(laptop);

        MongoDatabase database = mongoClientForTest.getDatabase(DATABASE_NAME);
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

        MongoDatabase database = mongoClientForTest.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(PRODUCT);
        Document doveSoapJson = getJSONDocument("{'name':'dove soap', 'price':22.2}");
        Document teaJson = getJSONDocument("{'name':'tea', 'price':12.3}");
        Document coffeeJson = getJSONDocument("{'name':'Coffee', 'price':12.5}");
        collection.insertMany(Arrays.asList(doveSoapJson, teaJson, coffeeJson));

        Product doveSoap = createProduct("dove soap", 22.2);
        Product tea = createProduct("tea", 12.3);
        Product coffee = createProduct("Coffee", 12.5);
        ProductDao dao = new ProductDao(mongoClient, DATABASE_NAME);

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
package dao;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import dto.Product;
import org.bson.Document;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by user on 5/8/15.
 */
public class ProductDaoSpec {
    @Test
    public void itShouldGetAllProducts() {
        String databaseName = "testdb";
        Product doveSoap = CreateProduct("dove soap", 22.2);
        Product tea = CreateProduct("tea", 12.3);
        Product coffee = CreateProduct("Coffee", 12.5);

        MongoServer server = new MongoServer(new MemoryBackend());
        InetSocketAddress inetSocketAddress = server.bind();
        ServerAddress serverAddress = new ServerAddress(inetSocketAddress);

        MongoClient mongoClientForTest = new MongoClient(serverAddress);
        MongoDatabase database = mongoClientForTest.getDatabase(databaseName);
        Document doveSoapJson = getJSONDocument("{'name':'dove soap', 'price':22.2}");
        Document teaJson = getJSONDocument("{'name':'tea', 'price':12.3}");
        Document coffeeJson = getJSONDocument("{'name':'Coffee', 'price':12.5}");
        MongoCollection<Document> collection = database.getCollection("Product");
        collection.insertMany(Arrays.asList(doveSoapJson, teaJson, coffeeJson));

        MongoClient mongoClient = new MongoClient(serverAddress);
        ProductDao dao = new ProductDao(mongoClient, databaseName);

        List products = dao.getAll();

        assertThat(products.contains(doveSoap), is(true));
        assertThat(products.contains(tea), is(true));
        assertThat(products.contains(coffee), is(true));
    }

    public Document getJSONDocument(String json) {
        return Document.parse(json);
    }

    public Product CreateProduct(String name, double price) {
        return new Product(name, price);
    }

}
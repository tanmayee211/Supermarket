package dao;

import com.mongodb.*;
import dto.Product;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

public class ProductDao {

    private final Datastore datastore;

    public ProductDao(MongoClient mongoClient , String databasename) {
        Morphia morphia = new Morphia();
        morphia.map(Product.class);
        datastore = morphia.createDatastore(mongoClient,databasename);
    }

    public List getAll() {
        return datastore.createQuery(Product.class).asList();
    }

    public ObjectId addProduct(Product product) {
        datastore.save(product);
        return product.getProductId();
    }
}
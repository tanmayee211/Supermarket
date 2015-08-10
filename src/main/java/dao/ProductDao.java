package dao;

import com.google.inject.Inject;
import com.mongodb.*;
import domain.Product;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

public class ProductDao {

    private final Datastore datastore;

    @Inject
    public ProductDao(MorphiaConfig morphiaConfig) {
        datastore = morphiaConfig.getDatastore();
    }

    public List getAll() {
        return datastore.createQuery(Product.class).asList();
    }

    public ObjectId addProduct(Product product) {
        datastore.save(product);
        return product.getProductId();
    }
}
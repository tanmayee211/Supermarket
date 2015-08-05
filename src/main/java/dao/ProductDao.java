package dao;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dto.Product;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.Collections;
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
}
package dao;

import com.mongodb.MongoClient;
import domain.Product;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by user on 7/8/15.
 */
public class MorphiaConfig {

    private final Datastore datastore;

    public MorphiaConfig() {
        this("appConfig.properties");
    }

    public MorphiaConfig( String configFileName) {
        Properties properties  = new Properties();
        InputStream propertiesStream = getClass().getClassLoader().getResourceAsStream(configFileName);
        try {
            properties.load(propertiesStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Morphia morphia = new Morphia();
        String packageName = properties.getProperty("morphia.scanPackage");
        morphia.mapPackage(packageName);

        String host = properties.getProperty("mongo.host");
        Integer port = Integer.parseInt(properties.getProperty("mongo.port"));
        MongoClient mongoClient = new MongoClient(host, port);

        String databaseName = properties.getProperty("mongo.databaseName");
        datastore = morphia.createDatastore(mongoClient, databaseName);
    }

    public Datastore getDatastore(){
        return this.datastore;
    }
}
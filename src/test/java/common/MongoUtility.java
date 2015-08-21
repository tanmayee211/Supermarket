package common;

import com.mongodb.MongoClient;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.h2.H2Backend;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by user on 19/8/15.
 */
public class MongoUtility {

    public static final String H2_BACKEND_FILE = "supermarket.mv";
    private static MongoServer mongoServer;
    private static String host;
    private static  Integer port;
    private static String dbName;

    public static void startServer(){
        File h2Backendfile = new File(H2_BACKEND_FILE);
        h2Backendfile.delete();

        Properties properties  = new Properties();
        String propFileName = "unitTestConfig.properties";
        InputStream propertiesStream = MongoUtility.class.getClassLoader().getResourceAsStream(propFileName);
        try {
            properties.load(propertiesStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
         host = properties.getProperty("mongo.host");
         port = Integer.parseInt(properties.getProperty("mongo.port"));
        dbName=properties.getProperty("mongo.databaseName");

        mongoServer = new MongoServer(new H2Backend(H2_BACKEND_FILE));
        mongoServer.bind(host, port);
    }

    public static MongoClient getClient()
    {
       return new MongoClient(host,port);
    }

    public static void stopServer()
    {
        mongoServer.shutdown();
    }

    public static String getDbName()
    {
        return dbName;
    }
}

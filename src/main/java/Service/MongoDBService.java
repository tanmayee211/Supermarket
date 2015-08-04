package Service;

import com.mongodb.*;

import java.util.List;

public class MongoDBService {

   final private  MongoClient mongoClient;

    public MongoDBService(String host,Integer port) {
        mongoClient = new MongoClient(host, port);

    }

    public boolean isCartExist(String dbName,String collectionName,String cartName) {
        boolean isCartExist=false;
        DBCollection coll = mongoClient.getDB(dbName).getCollection(collectionName);
        DBObject query = new BasicDBObject("name",cartName);
        DBCursor cartCursor = coll.find(query);
        if ( cartCursor.count()!=0) {
            isCartExist=true;
        }
        return isCartExist;
    }

    public List getProductList() {
        System.out.println("1");
        return null;
    }
}
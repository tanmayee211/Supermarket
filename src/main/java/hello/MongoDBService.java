package hello;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoDBService {

   final private  MongoClient mongoClient;

    public MongoDBService(String host,Integer port) {
        mongoClient = new MongoClient(host, port);

    }

    public void testDb(String dbName,String collectionName) {
        DBCollection coll = mongoClient.getDB(dbName).getCollection(collectionName);
        DBCursor cursor = coll.find();
        printCart(cursor);
    }

    public void printCart(DBCursor cursor) {
        int i = 1;
        while (cursor.hasNext()) {
            System.out.println("Inserted Document: " + i);
            System.out.println(cursor.next());
            i++;
        }
    }
}
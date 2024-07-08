package org.root.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.Closeable;

public class DatabaseConnection implements Closeable {
    private final MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
    private final MongoDatabase database = mongoClient.getDatabase("JavaUsers");
    private MongoCollection<Document> collection;

    public DatabaseConnection(String collectionName){
        this.collection = database.getCollection(collectionName);
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    @Override
    public void close(){
        if(mongoClient != null){
            mongoClient.close();
        }
    }
}
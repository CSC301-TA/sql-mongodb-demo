package ca.utoronto.utm.mcs;

import com.mongodb.client.*;
import org.bson.Document;

public class MongoDao {

    private MongoCollection<Document> collection;

    private final String username = "root";
    private final String password = "123456";
    private final String uriDb = String.format("mongodb://%s:%s@mongodb:27017", username, password);
    private final String dbName = "todos";

    public MongoDao() {
        MongoClient mongoClient = MongoClients.create(this.uriDb);
        MongoDatabase database = mongoClient.getDatabase(this.dbName);
        this.collection = database.getCollection(this.dbName);
    }

    public FindIterable<Document> getTodos() {
        try {
            return this.collection.find();
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
        return null;
    }

    public boolean patchTodo(int id, String title, String description, boolean isChecked) {
        // TO DO
        return false;
    }

    public boolean postTodo(String title, String description, boolean isChecked) {
        Document doc = new Document();
        doc.put("title", title);
        doc.put("description", description);
        doc.put("isChecked", isChecked);

        try {
            this.collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
        return false;
    }

    public boolean deleteTodo(int id) {
        // TO DO
        return false;
    }
}
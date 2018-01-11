package main.java.com.beadhouse.business.mongodbbusiness;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Map;

public class SaveToMongoDB implements AutoCloseable {
    private MongoClient mongoClient;

    public SaveToMongoDB() {
        mongoClient = new MongoClient("localhost", 27017);
    }

    public void insertToMongoDB(String msg) {
        Gson gson = new Gson();
        CommentStrcut commentStrcut = gson.fromJson(msg, CommentStrcut.class);
        MongoDatabase database = mongoClient.getDatabase(commentStrcut.getDatabase());
        MongoCollection<Document> documentMongoCollection = database.getCollection(commentStrcut.getCollection());
        Document document = new Document();
        for (Map.Entry<String, JsonElement> entry :
                gson.toJsonTree(commentStrcut.getCommentValue()).getAsJsonObject().entrySet()) {
            document.append(entry.getKey(), entry.getValue());
        }
        documentMongoCollection.insertOne(document);
    }

    @Override
    public void close() {
        mongoClient.close();
    }
}

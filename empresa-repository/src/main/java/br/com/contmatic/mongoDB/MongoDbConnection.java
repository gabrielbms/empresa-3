package br.com.contmatic.mongoDB;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDbConnection {

    private static final String HOST = "localhost";

    private static final String DB_NAME = "Empresa";

    private static MongoClient mongoClient;

    public static MongoDatabase getMongoDatabase() {
        mongoClient = new MongoClient(HOST);
        return mongoClient.getDatabase(DB_NAME);
    }

} 

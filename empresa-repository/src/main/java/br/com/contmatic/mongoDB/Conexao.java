package br.com.contmatic.mongoDB;

import java.io.Closeable;
import java.io.IOException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Conexao implements Closeable {
	
	private static MongoClient mongoClient;
	
	private static MongoDatabase database;
	
	private static Conexao conexao;

	private Conexao() {
	}

	public static Conexao getInstance() {
		if (mongoClient == null) {
			conexao = new Conexao();
			conexao.getMongoClient();
			conexao.getDatabase();
		}
		return conexao;
	}
	
	public MongoDatabase getDatabase() {
		if (database == null) {
			mongoClient = getMongoClient();
			database = mongoClient.getDatabase(MongoConf.DB_NAME);
		}
		return database;
	}

	public MongoClient getMongoClient() {
		if (mongoClient == null) {
			mongoClient = new MongoClient(MongoConf.HOST, MongoConf.PORT);

		}
		return mongoClient;
	}
	
	@Override
	public void close() throws IOException {
		mongoClient.close();
		mongoClient = null;
		database = null;
	}

}
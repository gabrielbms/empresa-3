package br.com.contmatic.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import br.com.contmatic.empresa.Cliente;

public class ClienteService {
	
	private String host = "localhost";

	private String database = "Empresa";

	private MongoCollection<Cliente> collection;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteService.class);

	private MongoDatabase getMongoDatabase() {
		MongoClient mongoClient = mongoClient();
		return mongoClient.getDatabase(database).withCodecRegistry(createCodecRegistry());
	}

	private MongoClient mongoClient() {
		return new MongoClient(host, MongoClientOptions.builder().codecRegistry(createCodecRegistry()).build());
	}
	
	private void closeConnection() {
		mongoClient().close();
	}

	private void connectCollection() {
		MongoDatabase mongoDatabase = getMongoDatabase().withCodecRegistry(createCodecRegistry());
		collection = mongoDatabase.getCollection("Cliente", Cliente.class).withCodecRegistry(createCodecRegistry());
	}
	
	private static CodecRegistry createCodecRegistry() {
		return CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
	}
	
	public void save(Cliente cliente) {
		try {
			connectCollection();
			CodecRegistry codecRegistry = createCodecRegistry();
			collection.withCodecRegistry(codecRegistry).insertOne(cliente);
			closeConnection();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}


	public void update(Cliente cliente) {
		try {
			connectCollection();
			CodecRegistry codecRegistry = createCodecRegistry();
			Bson findByCpf = Filters.eq("cpf", cliente.getCpf());
			collection.withCodecRegistry(codecRegistry).replaceOne(findByCpf, cliente);
			closeConnection();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public void deleteById(String id) throws IllegalAccessException {
		try {
			connectCollection();
			collection.deleteOne(Filters.eq("cpf", id));
			closeConnection();
		} catch (NullPointerException e) {
			LOGGER.error("Cliente não existe", e);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public Cliente findById(String id) {
		try {
		connectCollection();
		Bson findByCpf = Filters.eq("cpf", id);
		Cliente Cliente = collection.find(findByCpf).first();
		closeConnection();
		return Cliente;
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	public List<Cliente> findAll() {
		connectCollection();
		MongoCursor<Cliente> cursor = collection.find().iterator();
		List<Cliente> Clientes = new ArrayList<>();
		try {
			while (cursor.hasNext()) {
				Clientes.add(cursor.next());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			cursor.close();
		}
		closeConnection();
		return Clientes;
	}

}

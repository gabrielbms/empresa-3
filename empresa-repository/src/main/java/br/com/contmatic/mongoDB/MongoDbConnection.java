package br.com.contmatic.mongoDB;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Fornecedor;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;

public class MongoDbConnection {
    
    private static Logger LOGGER = LoggerFactory.getLogger(MongoDbConnection.class);;

    private static final String HOST = "localhost";
    
    private static final String DB_NAME = "Empresa";

    private static MongoClient mongoClient;

    private static MongoDatabase database = MongoDbConnection.getMongoDatabase();

    public static MongoDatabase getMongoDatabase() {
        mongoClient = new MongoClient(HOST);
        return mongoClient.getDatabase(DB_NAME);
    }

    public static String SentToDatabaseCliente(Cliente cliente) {
        MongoCollection<Document> clienteCollection = database.getCollection("Cliente");
        clienteCollection.insertOne(Document.parse(cliente.toString()).append("_id", cliente.getCpf()));
        return "Cadastro -> Documento nº" + clienteCollection.countDocuments() + "Inserido com sucesso";
    }
    
    public static void SentToDatabaseEmpresa(Empresa empresa) {
        MongoCollection<Document> empresaCollection = database.getCollection("Empresa");
        empresaCollection.insertOne(Document.parse(empresa.toString()).append("_id", empresa.getCnpj()));
        LOGGER.info("Empresa -> Documento nº " + empresaCollection.countDocuments() + " Inserido com sucesso");
    }
    
    public static String SentToDatabaseFornecedor(Fornecedor fornecedor) {
        MongoCollection<Document> cadastroCollection = database.getCollection("Fornecedor");
        cadastroCollection.insertOne(Document.parse(fornecedor.toString()).append("_id", fornecedor.getCnpj()));
        return "Cadastro -> Documento nº" + cadastroCollection.countDocuments() + "Inserido com sucesso";
    }

    public static String SentToDatabaseFuncionario(Funcionario funcionario) {
        MongoCollection<Document> funcionarioCollection = database.getCollection("Funcionario");
        funcionarioCollection.insertOne(Document.parse(funcionario.toString()).append("_id", funcionario.getCpf()));
        return "Funcionario -> Documento nº" + funcionarioCollection.countDocuments() + "Inserido com sucesso";
    }
    
    public static String SentToDatabaseProduto(Produto produto) {
    	MongoCollection<Document> produtoCollection = database.getCollection("Produto");
    	produtoCollection.insertOne(Document.parse(produto.toString()).append("_id", produto.getId()));
    	return "Produto -> Id n°" + produtoCollection.countDocuments() + "Inserido com sucesso";
    }

}

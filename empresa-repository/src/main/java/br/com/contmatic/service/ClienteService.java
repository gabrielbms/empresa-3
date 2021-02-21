package br.com.contmatic.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

import br.com.contmatic.assembly.ClienteResourceAssembly;
import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.mongoDB.MongoDbConnection;
import br.com.contmatic.repository.ClienteRepository;

public class ClienteService implements ClienteRepository{
    
    private ClienteResourceAssembly assembly = new ClienteResourceAssembly();

    private MongoDatabase database = MongoDbConnection.getMongoDatabase();
    
    MongoCollection<Document> clienteCollection = database.getCollection("Cliente");

    @Override
    public String save(Cliente cliente) {
        clienteCollection.insertOne(Document.parse(cliente.toString()));
        return "Cadastro -> Cliente nº" + clienteCollection.countDocuments() + "Inserido com sucesso";
    }
    
    @Override
    public void update(Cliente cliente) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("cpf", cliente.getCpf());
        Document clienteDocument = assembly.toDocument(cliente);
        clienteCollection.replaceOne(whereQuery, clienteDocument);
    }
    
    @Override
    public void updateByField(String campo, String conteudo, Cliente cliente) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, conteudo);
        Document clienteDocument = assembly.toDocument(cliente);
        clienteCollection.replaceOne(whereQuery, clienteDocument);
    }

    @Override
    public void deleteById(String cpf) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("cpf", cpf);
        clienteCollection.deleteOne(whereQuery);
    }
    
    @Override
    public void deleteByField(String campo, String counteudo, Cliente cliente) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, counteudo);
        clienteCollection.deleteOne(whereQuery);
    }
    
    @Override
    public Cliente findById(String cpf) {    
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("cpf", cpf);
        FindIterable<Document> find = database.getCollection("Cliente").find(whereQuery);
        Cliente cliente = new Cliente();
        for(Document doc : find) {
            cliente = assembly.toResource(doc);
        }
        return cliente;
    }
    
    @Override
    public Cliente findAndReturnsSelectedFields(String campo, String conteudoCampo, List<String> conteudo) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, conteudoCampo);
        FindIterable<Document> find = clienteCollection.find(whereQuery).projection(Projections.include(conteudo));
        Cliente cliente = new Cliente();
        for(Document doc : find) {
            cliente = assembly.toResource(doc);
        }
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        MongoCursor<Document> cursor = clienteCollection.find().iterator();
        while (cursor.hasNext()) {
            clientes.add(assembly.toResource(cursor.next()));
        }
        return clientes;
    }

}

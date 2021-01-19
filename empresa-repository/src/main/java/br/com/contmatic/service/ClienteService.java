package br.com.contmatic.service;

import static com.mongodb.client.model.Projections.include;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.assembly.ClienteResourceAssembly;
import br.com.contmatic.empresa.Cliente;

public class ClienteService {

    private static final String NAME_COLLECTION = "Cliente";

    private MongoDatabase database;

    public ClienteService(MongoDatabase database) {
        this.database = database;
    }

    public void salvar(Cliente cliente) throws IOException {
        Document document = Document.parse(cliente.toString());
        document.append("_id", cliente.getCpf());
        database.getCollection(NAME_COLLECTION).insertOne(document);
    }

    public void alterar(Cliente cliente) {
        Document document = Document.parse(cliente.toString());
        document.remove("cpf");
        document.append("_id", cliente.getCpf());

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", cliente.getCpf());

        database.getCollection(NAME_COLLECTION).updateOne(whereQuery, new Document("$set", document));
    }

    public void deletar(Cliente cliente) throws IOException {
        Document document = new Document("_id", cliente.getCpf());
        document.remove("cpf");
        document.append("_id", cliente.getCpf());
        database.getCollection(NAME_COLLECTION).deleteOne(new Document("_id", cliente.getCpf()));
    }

    public Cliente selecionar(String _id) throws IOException {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", _id);
        FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find(whereQuery);
        return new ClienteResourceAssembly().toResource(find.first());
    }

    public List<Cliente> selecionar() throws IOException {
        FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find();
        List<Cliente> clientes = new ArrayList<Cliente>();
        ClienteResourceAssembly clienteResourceAssembly = new ClienteResourceAssembly();
        for(Document document : find) {
            clientes.add(clienteResourceAssembly.toResource(document));
        }
        return clientes;
    }

    public List<Cliente> selecionar(List<String> campos) throws IOException {
        List<Cliente> clientes = null;
        if (campos == null) {
            return clientes;
        }
        if (campos.isEmpty()) {
            return clientes;
        }
        clientes = new ArrayList<Cliente>();
        FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find().projection(include(campos)).limit(50);

        ClienteResourceAssembly clienteResourceAssembly = new ClienteResourceAssembly();
        for(Document document : find) {
            clientes.add(clienteResourceAssembly.toResource(document));
        }
        return clientes;
    }

}

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

import br.com.contmatic.assembly.FornecedorResourceAssembly;
import br.com.contmatic.empresa.Fornecedor;
import br.com.contmatic.mongoDB.MongoDbConnection;
import br.com.contmatic.repository.FornecedorRepository;

public class FornecedorService implements FornecedorRepository {

    private FornecedorResourceAssembly assembly = new FornecedorResourceAssembly();

    private MongoDatabase database = MongoDbConnection.getMongoDatabase();
    
    MongoCollection<Document> fornecedorCollection = database.getCollection("Fornecedor");

    @Override
    public String save(Fornecedor fornecedor) {
        fornecedorCollection.insertOne(Document.parse(fornecedor.toString()).append("_id", fornecedor.getCnpj()));
        return "Cadastro -> fornecedor nº" + fornecedorCollection.countDocuments() + "Inserido com sucesso";
    }
    
    @Override
    public void update(Fornecedor fornecedor) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", fornecedor.getCnpj());
        Document fornecedorDocument = assembly.toDocument(fornecedor);
        fornecedorCollection.replaceOne(whereQuery, fornecedorDocument);
    }
    
    @Override
    public void updateByField(String campo, String conteudo, Fornecedor fornecedor) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, conteudo);
        Document empresaDocument = assembly.toDocument(fornecedor);
        fornecedorCollection.replaceOne(whereQuery, empresaDocument);
    }

    @Override
    public void deleteById(String _id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", _id);
        fornecedorCollection.deleteOne(whereQuery);
    }
    
    @Override
    public void deleteByField(String campo, String counteudo, Fornecedor fornecedor) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, counteudo);
        fornecedorCollection.deleteOne(whereQuery);
    }
    
    @Override
    public Fornecedor findById(String _id) {    
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", _id);
        FindIterable<Document> find = database.getCollection("Fornecedor").find(whereQuery);
        Fornecedor fornecedor = new Fornecedor();
        for(Document doc : find) {
            fornecedor = assembly.toResource(doc);
        }
        return fornecedor;
    }
    
    @Override
    public Fornecedor findAndReturnsSelectedFields(String campo, String conteudoCampo, List<String> conteudo) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, conteudoCampo);
        FindIterable<Document> find = fornecedorCollection.find(whereQuery).projection(Projections.include(conteudo));
        Fornecedor fornecedor = new Fornecedor();
        for(Document doc : find) {
            fornecedor = assembly.toResource(doc);
        }
        return fornecedor;
    }

    @Override
    public List<Fornecedor> findAll() {
        List<Fornecedor> fornecedoress = new ArrayList<>();
        MongoCursor<Document> cursor = fornecedorCollection.find().iterator();
        while (cursor.hasNext()) {
            fornecedoress.add(assembly.toResource(cursor.next()));
        }
        return fornecedoress;
    }

}

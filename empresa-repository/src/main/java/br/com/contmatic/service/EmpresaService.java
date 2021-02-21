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

import br.com.contmatic.assembly.EmpresaResourceAssembly;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.mongoDB.MongoDbConnection;
import br.com.contmatic.repository.EmpresaRepository;

public class EmpresaService implements EmpresaRepository {

    private EmpresaResourceAssembly assembly = new EmpresaResourceAssembly();

    private MongoDatabase database = MongoDbConnection.getMongoDatabase();
    
    MongoCollection<Document> empresaCollection = database.getCollection("Empresa");

    @Override
    public String save(Empresa empresa) {
        empresaCollection.insertOne(Document.parse(empresa.toString()).append("_id", empresa.getCnpj()));
        return "Cadastro -> empresa nº" + empresaCollection.countDocuments() + "Inserido com sucesso";
    }
    
    @Override
    public void update(Empresa empresa) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", empresa.getCnpj());
        Document empresaDocument = assembly.toDocument(empresa);
        empresaCollection.replaceOne(whereQuery, empresaDocument);
    }
    
    @Override
    public void updateByField(String campo, String conteudo, Empresa empresa) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, conteudo);
        Document empresaDocument = assembly.toDocument(empresa);
        empresaCollection.replaceOne(whereQuery, empresaDocument);
    }

    @Override
    public void deleteById(String _id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", _id);
        empresaCollection.deleteOne(whereQuery);
    }
    
    @Override
    public void deleteByField(String campo, String counteudo, Empresa empresa) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, counteudo);
        empresaCollection.deleteOne(whereQuery);
    }
    
    @Override
    public Empresa findById(String _id) {    
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", _id);
        FindIterable<Document> find = database.getCollection("Empresa").find(whereQuery);
        Empresa empresa = new Empresa();
        for(Document doc : find) {
            empresa = assembly.toResource(doc);
        }
        return empresa;
    }
    
    @Override
    public Empresa findAndReturnsSelectedFields(String campo, String conteudoCampo, List<String> conteudo) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, conteudoCampo);
        FindIterable<Document> find = empresaCollection.find(whereQuery).projection(Projections.include(conteudo));
        Empresa empresa = new Empresa();
        for(Document doc : find) {
            empresa = assembly.toResource(doc);
        }
        return empresa;
    }

    @Override
    public List<Empresa> findAll() {
        List<Empresa> empresas = new ArrayList<>();
        MongoCursor<Document> cursor = empresaCollection.find().iterator();
        while (cursor.hasNext()) {
            empresas.add(assembly.toResource(cursor.next()));
        }
        return empresas;
    }

}

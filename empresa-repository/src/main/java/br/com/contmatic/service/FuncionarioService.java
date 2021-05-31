package br.com.contmatic.service;

import static br.com.contmatic.util.BsonUtil.removeFieldFromDocument;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

import br.com.contmatic.assembly.FuncionarioResourceAssembly;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.mongoDB.MongoDbConnection;
import br.com.contmatic.repository.FuncionarioRepository;

public class FuncionarioService implements FuncionarioRepository {

    private FuncionarioResourceAssembly assembly = new FuncionarioResourceAssembly();

    private MongoDatabase database = MongoDbConnection.getMongoDatabase();
    
    MongoCollection<Document> funcionarioCollection = database.getCollection("Funcionario");

    @Override
    public String save(Funcionario funcionario) {
        Document document = Document.parse(funcionario.toString()).append("_id", funcionario.getCpf());
        removeFieldFromDocument(document, "cpf");
        funcionarioCollection.insertOne(document);
        return "Cadastro -> funcionario nº" + funcionarioCollection.countDocuments() + "Inserido com sucesso";
    }
    
    @Override
    public void update(Funcionario funcionario) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", funcionario.getCpf());
        Document funcionarioDocument = assembly.toDocument(funcionario);
        funcionarioCollection.replaceOne(whereQuery, funcionarioDocument);
    }
    
    @Override
    public void updateByField(String campo, String conteudo, Funcionario funcionario) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, conteudo);
        Document funcionarioDocument = assembly.toDocument(funcionario);
        funcionarioCollection.replaceOne(whereQuery, funcionarioDocument);
    }

    @Override
    public void deleteById(String _id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", _id);
        funcionarioCollection.deleteOne(whereQuery);
    }
    
    @Override
    public void deleteByField(String campo, String counteudo, Funcionario funcionario) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, counteudo);
        funcionarioCollection.deleteOne(whereQuery);
    }
    
    @Override
    public Funcionario findById(String _id) {    
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", _id);
        FindIterable<Document> find = database.getCollection("Funcionario").find(whereQuery);
        Funcionario funcionario = new Funcionario();
        for(Document doc : find) {
            funcionario = assembly.toResource(doc);
        }
        return funcionario;
    }
    
    @Override
    public Funcionario findAndReturnsSelectedFields(String campo, String conteudoCampo, List<String> conteudo) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, conteudoCampo);
        FindIterable<Document> find = funcionarioCollection.find(whereQuery).projection(Projections.include(conteudo));
        Funcionario funcionario = new Funcionario();
        for(Document doc : find) {
            funcionario = assembly.toResource(doc);
        }
        return funcionario;
    }

    @Override
    public List<Funcionario> findAll() {
        List<Funcionario> funcionarios = new ArrayList<>();
        MongoCursor<Document> cursor = funcionarioCollection.find().iterator();
        while (cursor.hasNext()) {
            funcionarios.add(assembly.toResource(cursor.next()));
        }
        return funcionarios;
    }

}

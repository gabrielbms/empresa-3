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

import br.com.contmatic.assembly.ProdutoResourceAssembly;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.mongoDB.MongoDbConnection;
import br.com.contmatic.repository.ProdutoRepository;

public class ProdutoService implements ProdutoRepository{

    private ProdutoResourceAssembly assembly = new ProdutoResourceAssembly();

    private MongoDatabase database = MongoDbConnection.getMongoDatabase();
    
    MongoCollection<Document> produtoCollection = database.getCollection("Produto");

    @Override
    public String save(Produto produto) {
        Document document = Document.parse(produto.toString()).append("_id", produto.getId());
        removeFieldFromDocument(document, "id");
        produtoCollection.insertOne(document);
        return "Cadastro -> produto nº" + produtoCollection.countDocuments() + "Inserido com sucesso";
    }
    
    @Override
    public void update(Produto produto) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", produto.getId());
        Document produtoDocument = assembly.toDocument(produto);
        produtoCollection.replaceOne(whereQuery, produtoDocument);
    }
    
    public void updateByField(String campo, String conteudo, Produto produto) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, conteudo);
        Document produtoDocument = assembly.toDocument(produto);
        produtoCollection.replaceOne(whereQuery, produtoDocument);
    }

    @Override
    public void deleteById(Integer _id) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", _id);
        produtoCollection.deleteOne(whereQuery);
    }
    
    public void deleteByField(String campo, String counteudo, Produto produto) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, counteudo);
        produtoCollection.deleteOne(whereQuery);
    }
    
    @Override
    public Produto findById(Integer _id) {    
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", _id);
        FindIterable<Document> find = database.getCollection("Produto").find(whereQuery);
        Produto produto = new Produto();
        for(Document doc : find) {
            produto = assembly.toResource(doc);
        }
        return produto;
    }
    
    public Produto findAndReturnsSelectedFields(String campo, Integer conteudoCampo, List<String> conteudo) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append(campo, conteudoCampo);
        FindIterable<Document> find = produtoCollection.find(whereQuery).projection(Projections.include(conteudo));
        Produto produto = new Produto();
        for(Document doc : find) {
            produto = assembly.toResource(doc);
        }
        return produto;
    }

    @Override
    public List<Produto> findAll() {
        List<Produto> produtos = new ArrayList<>();
        MongoCursor<Document> cursor = produtoCollection.find().iterator();
        while (cursor.hasNext()) {
            produtos.add(assembly.toResource(cursor.next()));
        }
        return produtos;
    }

}

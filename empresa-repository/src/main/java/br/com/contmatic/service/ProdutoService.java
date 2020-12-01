package br.com.contmatic.service;

import static com.mongodb.client.model.Projections.include;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.assembly.ProdutoResourceAssembly;
import br.com.contmatic.empresa.Produto;

public class ProdutoService {

    private static final String NAME_COLLECTION = "Produto";

    private MongoDatabase database;

    public ProdutoService(MongoDatabase database) {
        this.database = database;
    }

    public void salvar(Produto produto) throws IOException {
        Document document = Document.parse(produto.toString());
        document.append("_id", produto.getId());
        database.getCollection(NAME_COLLECTION).insertOne(document);

    }

    public void alterar(Document query, Document where) {
        database.getCollection(NAME_COLLECTION).updateMany(where, new Document("$set", query));
    }

    public void alterar(Produto produto) {
        Document document = Document.parse(produto.toString());
        document.remove("Id");
        document.append("_id", produto.getId());

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", produto.getId());

        database.getCollection(NAME_COLLECTION).updateOne(whereQuery, new Document("$set", document));
    }

    public void deletar(Document document) {
        database.getCollection(NAME_COLLECTION).deleteMany(document);
    }

    public void deletar(Produto Produto) throws IOException {
        Document document = new Document("_id", Produto.getId());
        document.remove("Id");
        document.append("_id", Produto.getId());
        database.getCollection(NAME_COLLECTION).deleteOne(new Document("_id", Produto.getId()));
    }

    public Produto selecionar(Integer _id) throws IOException {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", _id);
        FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find(whereQuery);
        return new ProdutoResourceAssembly().toResource(find.first());
    }

    public List<Produto> selecionar() throws IOException {
        FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find();
        List<Produto> Produtos = new ArrayList<Produto>();
        ProdutoResourceAssembly ProdutoResourceAssembly = new ProdutoResourceAssembly();
        for(Document document : find) {
            Produtos.add(ProdutoResourceAssembly.toResource(document));
        }
        return Produtos;
    }

    public List<Produto> selecionar(List<String> campos) throws IOException {
        List<Produto> Produtos = null;
        if (campos == null) {
            return Produtos;
        }
        if (campos.isEmpty()) {
            return Produtos;
        }
        Produtos = new ArrayList<Produto>();
        FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find().projection(include(campos)).limit(50);

        ProdutoResourceAssembly ProdutoResourceAssembly = new ProdutoResourceAssembly();
        for(Document document : find) {
            Produtos.add(ProdutoResourceAssembly.toResource(document));
        }
        return Produtos;
    }

}

package br.com.contmatic.service;

import static com.mongodb.client.model.Projections.include;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.assembly.FornecedorResourceAssembly;
import br.com.contmatic.empresa.Fornecedor;

public class FornecedorService {

    private static final String NAME_COLLECTION = "Fornecedor";

    private MongoDatabase database;

    public FornecedorService(MongoDatabase database) {
        this.database = database;
    }

    public void salvar(Fornecedor fornecedor) throws IOException {
        Document document = Document.parse(fornecedor.toString());
        document.append("_id", fornecedor.getCnpj());
        database.getCollection(NAME_COLLECTION).insertOne(document);

    }

    public void alterar(Document query, Document where) {
        database.getCollection(NAME_COLLECTION).updateMany(where, new Document("$set", query));
    }

    public void alterar(Fornecedor fornecedor) {
        Document document = Document.parse(fornecedor.toString());
        document.remove("cnpj");
        document.append("_id", fornecedor.getCnpj());

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", fornecedor.getCnpj());

        database.getCollection(NAME_COLLECTION).updateOne(whereQuery, new Document("$set", document));
    }

    public void deletar(Document document) {
        database.getCollection(NAME_COLLECTION).deleteMany(document);
    }

    public void deletar(Fornecedor fornecedor) throws IOException {
        Document document = new Document("_id", fornecedor.getCnpj());
        document.remove("cnpj");
        document.append("_id", fornecedor.getCnpj());
        database.getCollection(NAME_COLLECTION).deleteOne(new Document("_id", fornecedor.getCnpj()));
    }

    public Fornecedor selecionar(String _id) throws IOException {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", _id);
        FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find(whereQuery);
        return new FornecedorResourceAssembly().toResource(find.first());
    }

    public List<Fornecedor> selecionar() throws IOException {
        FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find();
        List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
        FornecedorResourceAssembly fornecedorResourceAssembly = new FornecedorResourceAssembly();
        for(Document document : find) {
            fornecedores.add(fornecedorResourceAssembly.toResource(document));
        }
        return fornecedores;
    }

    public List<Fornecedor> selecionar(List<String> campos) throws IOException {
        List<Fornecedor> fornecedores = null;
        if (campos == null) {
            return fornecedores;
        }
        if (campos.isEmpty()) {
            return fornecedores;
        }
        fornecedores = new ArrayList<Fornecedor>();
        FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find().projection(include(campos)).limit(50);

        FornecedorResourceAssembly fornecedorResourceAssembly = new FornecedorResourceAssembly();
        for(Document document : find) {
            fornecedores.add(fornecedorResourceAssembly.toResource(document));
        }
        return fornecedores;
    }

}

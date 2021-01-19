package br.com.contmatic.service;

import static com.mongodb.client.model.Projections.include;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.assembly.EmpresaResourceAssembly;
import br.com.contmatic.empresa.Empresa;

public class EmpresaService {

    private static final String NAME_COLLECTION = "Empresa";

    private MongoDatabase database;

    public EmpresaService(MongoDatabase database) {
        this.database = database;
    }

    public void salvar(Empresa empresa) throws IOException {
        Document document = Document.parse(empresa.toString());
        document.append("_id", empresa.getCnpj());
        database.getCollection(NAME_COLLECTION).insertOne(document);
    }

    public void alterar(Empresa empresa) {
        Document document = Document.parse(empresa.toString());
        document.remove("cnpj");
        document.append("_id", empresa.getCnpj());

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", empresa.getCnpj());

        database.getCollection(NAME_COLLECTION).updateOne(whereQuery, new Document("$set", document));
    }

    public void deletar(Empresa empresa) throws IOException {
        Document document = new Document("_id", empresa.getCnpj());
        document.remove("cnpj");
        document.append("_id", empresa.getCnpj());
        database.getCollection(NAME_COLLECTION).deleteOne(new Document("_id", empresa.getCnpj()));
    }

    public Empresa selecionar(String _id) throws IOException {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.append("_id", _id);
        FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find(whereQuery);
        return new EmpresaResourceAssembly().toResource(find.first());
    }

    public List<Empresa> selecionar() throws IOException {
        FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find();
        List<Empresa> empresas = new ArrayList<Empresa>();
        EmpresaResourceAssembly empresaResourceAssembly = new EmpresaResourceAssembly();
        for(Document document : find) {
            empresas.add(empresaResourceAssembly.toResource(document));
        }
        return empresas;
    }

    public List<Empresa> selecionar(List<String> campos) throws IOException {
        List<Empresa> empresas = null;
        if (campos == null) {
            return empresas;
        }
        if (campos.isEmpty()) {
            return empresas;
        }
        empresas = new ArrayList<Empresa>();
        FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find().projection(include(campos)).limit(50);

        EmpresaResourceAssembly empresaResourceAssembly = new EmpresaResourceAssembly();
        for(Document document : find) {
            empresas.add(empresaResourceAssembly.toResource(document));
        }
        return empresas;
    }

}

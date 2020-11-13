package br.com.contmatic.service;

import static com.mongodb.client.model.Projections.include;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.assembly.FuncionarioResourceAssembly;
import br.com.contmatic.empresa.Funcionario;

public class FuncionarioService {

private static final String NAME_COLLECTION = "Funcionario";
	
	private MongoDatabase database;
	
	public FuncionarioService(MongoDatabase database) {
		this.database = database;
	}

	public void salvar(Funcionario Funcionario) throws IOException {
		Document document = Document.parse(Funcionario.toString());
		document.append("_id", Funcionario.getCpf());
		database.getCollection(NAME_COLLECTION).insertOne(document);

	}
	
	public void alterar(Document query, Document where) {
		database.getCollection(NAME_COLLECTION).updateMany(where, new Document("$set", query));
	}
	
	public void alterar(Funcionario Funcionario) {
		Document document = Document.parse(Funcionario.toString());
		document.remove("cnpj");
		document.append("_id", Funcionario.getCpf());

		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("_id", Funcionario.getCpf());

		database.getCollection(NAME_COLLECTION).updateOne(whereQuery, new Document("$set", document));
	}

	public void deletar(Document document) {
		database.getCollection(NAME_COLLECTION).deleteMany(document);
	}
	
	public void deletar(Funcionario Funcionario) throws IOException {
		Document document = new Document("_id", Funcionario.getCpf());
		document.remove("cnpj");
		document.append("_id", Funcionario.getCpf());
		database.getCollection(NAME_COLLECTION).deleteOne(new Document("_id", Funcionario.getCpf()));
	}
	
	public Funcionario selecionar(String _id) throws IOException {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.append("_id", _id);
		FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find(whereQuery);
		return new FuncionarioResourceAssembly().toResource(find.first());
	}
	
	public List<Funcionario> selecionar() throws IOException {
		FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find();
		List<Funcionario> Funcionarios = new ArrayList<Funcionario>();
		FuncionarioResourceAssembly FuncionarioResourceAssembly = new FuncionarioResourceAssembly();
		for (Document document : find) {
			Funcionarios.add(FuncionarioResourceAssembly.toResource(document));
		}
		return Funcionarios;
	}
	
	public List<Funcionario> selecionar(List<String> campos) throws IOException {
		List<Funcionario> Funcionarios = null;
		if (campos == null) {
			return Funcionarios;
		}
		if (campos.isEmpty()) {
			return Funcionarios;
		}
		Funcionarios = new ArrayList<Funcionario>();
		FindIterable<Document> find = database.getCollection(NAME_COLLECTION).find().projection(include(campos))
				.limit(50);

		FuncionarioResourceAssembly FuncionarioResourceAssembly = new FuncionarioResourceAssembly();
		for (Document document : find) {
			Funcionarios.add(FuncionarioResourceAssembly.toResource(document));
		}
		return Funcionarios;
	}
	
}

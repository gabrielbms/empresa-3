package br.com.contmatic.assembly;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.endereco.Endereco;

public class EmpresaResourceAssembly implements Assembly<Empresa, Document> {
	
	@Override
	public Empresa toResource(Document document) {
		if (document != null) {
			Empresa resource = new Empresa();
			resource.setCnpj(document.getString("cnpj"));
			resource.setNome(document.getString("nome"));
			resource.setSite(document.getString("site"));
			resource.setTelefones(toResourceTelefones(document.getList("telefones", Document.class)));
			resource.setEnderecos(toResourceEnderecos(document.getList("enderecos", Document.class)));
			return resource;
		}
		return null;
	}
	
	@Override
	public Document toDocument(Empresa resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

	private Set<Endereco> toResourceEnderecos(List<Document> documents) {
		Set<Endereco> resources = null;
		if (documents == null) {
			return resources;
		}
		resources = new HashSet<Endereco>();
		EnderecoResourceAssembly resource = new EnderecoResourceAssembly();
		for (Document document : documents) {
			resources.add(resource.toResource(document));
		}
		return resources;
	}
	
	private Set<Telefone> toResourceTelefones(List<Document> documents) {
		Set<Telefone> telefones = null;
		if (documents == null) {
			return telefones;
		}
		TelefoneResourceAssembly assembly = new TelefoneResourceAssembly();
		telefones = new HashSet<Telefone>();
		for (Document document : documents) {
			telefones.add(assembly.toResource(document));
		}

		return telefones;
	}
	
}

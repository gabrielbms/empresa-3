package br.com.contmatic.assembly;

import org.bson.Document;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.Estado;

public class EnderecoResourceAssembly implements Assembly<Endereco, Document> {
	
	@Override
	public Endereco toResource(Document document) {
		if (document != null) {
			Endereco resource = new Endereco();
			resource.setCep(document.getString("cep"));
			resource.setRua(document.getString("rua"));
			resource.setNumero(document.getInteger("numero"));
			resource.setComplemento(document.getString("complemento"));
			resource.setBairro(document.getString("bairro"));
			resource.setCidade(document.getString("cidade"));
			resource.setEstado(Estado.valueOf(document.getString("estado")));
			return resource;
		}
		return null;
	}

	@Override
	public Document toDocument(Endereco resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}
	
}

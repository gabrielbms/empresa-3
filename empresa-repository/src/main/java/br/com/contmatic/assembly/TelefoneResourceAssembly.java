package br.com.contmatic.assembly;

import org.bson.Document;

import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDD;
import br.com.contmatic.telefone.TipoTelefone;

public class TelefoneResourceAssembly implements Assembly<Telefone, Document> {
	
	@Override
	public Telefone toResource(Document document) {
		if (document != null) {
			Telefone resource = new Telefone();
			resource.setTipoTelefone(TipoTelefone.valueOf(document.getString("tipoTelefone")));
			resource.setDdd(TelefoneDDD.valueOf(document.getString("ddd")));
			resource.setNumero(document.getString("numero"));
			return resource;
		}
		return null;
	}
	
	@Override
	public Document toDocument(Telefone resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

}

package br.com.contmatic.assembly;

import org.bson.Document;

import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDDType;
import br.com.contmatic.telefone.TelefoneType;

public class TelefoneResourceAssembly implements Assembly<Telefone, Document> {
	
	@Override
	public Telefone toResource(Document document) {
		if (document != null) {
			Telefone resource = new Telefone();
			resource.setTipoTelefone(TelefoneType.valueOf(document.getString("tipoTelefone")));
			resource.setDdd(TelefoneDDDType.valueOf(document.getString("ddd")));
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

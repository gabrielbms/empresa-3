package br.com.contmatic.assembly;

import java.math.BigDecimal;

import org.bson.Document;

import br.com.contmatic.empresa.Produto;

public class ProdutoResourceAssembly  implements Assembly<Produto, Document> {
	
	@Override
	public Produto toResource(Document document) {
		if (document != null) {
			Produto resource = new Produto();
			resource.setId(document.getInteger("id"));
			resource.setNome(document.getString("nome"));
			resource.setQuantidade(document.getInteger("quantidade"));
			BigDecimal preco = validarPreco(document);
			resource.setPreco(preco);
			return resource;
		}
		return null;
	}
	
	private static BigDecimal validarPreco(Document document) {
		if (document.getDouble("preco") != null) {
			Double precoDouble = document.getDouble("preco");
			BigDecimal precoBigDecimal = BigDecimal.valueOf(precoDouble).setScale(2);
			return precoBigDecimal;
		} 
		return BigDecimal.valueOf(0);
	}
	
	@Override
	public Document toDocument(Produto resource) {
		if (resource != null) {
			return Document.parse(resource.toString());
		}
		return null;
	}

}

package br.com.contmatic.assembly;

import org.bson.Document;

public interface Assembly <T, D extends Document> {
	
	T toResource(D document);
	
	D toDocument(T resource);

}

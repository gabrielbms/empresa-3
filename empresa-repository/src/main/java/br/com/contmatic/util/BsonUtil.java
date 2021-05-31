package br.com.contmatic.util;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.DBObject;

public class BsonUtil {

    public static void removeFieldFromDocument(Bson bson, String key) {
        if (bson instanceof Document) {
            ((Document) bson).remove(key);
            return;
        }
        if (bson instanceof DBObject) {
            ((DBObject) bson).removeField(key);
            return;
        }
        throw new IllegalArgumentException(String.format("Não foi possivel remover o campo %s do documento.", bson.getClass()));
    }

}

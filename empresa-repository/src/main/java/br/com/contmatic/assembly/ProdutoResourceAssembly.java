package br.com.contmatic.assembly;

import java.math.BigDecimal;

import org.bson.Document;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.contmatic.empresa.Produto;

public class ProdutoResourceAssembly implements Assembly<Produto, Document> {

    @Override
    public Produto toResource(Document document) {
        if (document != null) {
            Produto resource = new Produto();
            resource.setId(document.getInteger("id"));
            resource.setNome(document.getString("nome"));
            resource.setQuantidade(document.getInteger("quantidade"));
            BigDecimal preco = validarPreco(document);
            resource.setPreco(preco);
            resource.setDataCriacao(toDateTime(document.getString("dataCriacao")));
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
    
    private DateTime toDateTime(String dataCriacao) {
        if (dataCriacao != null) {
            String dia = dataCriacao.substring(8, 10);
            String mes = dataCriacao.substring(5, 7);
            String ano = dataCriacao.substring(0, 4);
            String hora = dataCriacao.substring(11, 13);
            String minuto = dataCriacao.substring(14, 16);
            String segundo = dataCriacao.substring(17, 19);
            String milisegundo = dataCriacao.substring(20, 23);
            String dataString = dia + "/" + mes + "/" + ano + " " + hora + ":" + minuto  + ":" + segundo + ":" + milisegundo;
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss:SSS");
            DateTime dataFormatada = formatter.parseDateTime(dataString);
            return dataFormatada;
        }
        return null;
    }


}

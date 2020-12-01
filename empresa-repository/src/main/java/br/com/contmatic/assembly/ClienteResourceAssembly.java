package br.com.contmatic.assembly;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.telefone.Telefone;

public class ClienteResourceAssembly implements Assembly<Cliente, Document> {

    @Override
    public Cliente toResource(Document document) {
        if (document != null) {
            Cliente resource = new Cliente();
            resource.setCpf(document.getString("cpf"));
            resource.setNome(document.getString("nome"));
            resource.setEmail(document.getString("email"));
            BigDecimal valorBoleto = validarBoleto(document);
            resource.setBoleto(valorBoleto);
            resource.setTelefones(toResourceTelefones(document.getList("telefones", Document.class)));
            return resource;
        }
        return null;
    }

    private static BigDecimal validarBoleto(Document document) {
        if (document.getDouble("boleto") != null) {
            Double boletoDouble = document.getDouble("boleto");
            BigDecimal boletoBigDecimal = BigDecimal.valueOf(boletoDouble).setScale(2);
            return boletoBigDecimal;
        }
        return BigDecimal.valueOf(0);
    }

    @Override
    public Document toDocument(Cliente resource) {
        if (resource != null) {
            return Document.parse(resource.toString());
        }
        return null;
    }

    private Set<Telefone> toResourceTelefones(List<Document> documents) {
        Set<Telefone> telefones = null;
        if (documents == null) {
            return telefones;
        }
        TelefoneResourceAssembly assembly = new TelefoneResourceAssembly();
        telefones = new HashSet<Telefone>();
        for(Document document : documents) {
            telefones.add(assembly.toResource(document));
        }

        return telefones;
    }
}

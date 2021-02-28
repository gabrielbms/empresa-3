package br.com.contmatic.assembly;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import br.com.contmatic.empresa.Fornecedor;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.endereco.Endereco;

public class FornecedorResourceAssembly implements Assembly<Fornecedor, Document> {

    @Override
    public Fornecedor toResource(Document document) {
        if (document != null) {
            Fornecedor resource = new Fornecedor();
            resource.setCnpj(document.getString("cnpj"));
            resource.setNome(document.getString("nome"));
            resource.setProduto(toResourceProdutos(document.getList("produtos", Document.class)));
            resource.setTelefones(toResourceTelefones(document.getList("telefones", Document.class)));
            resource.setEnderecos(toResourceEnderecos(document.getList("enderecos", Document.class)));
            resource.setDataCriacao(toDateTime(document.getString("dataCriacao")));
            return resource;
        }
        return null;
    }

    @Override
    public Document toDocument(Fornecedor resource) {
        if (resource != null) {
            return Document.parse(resource.toString());
        }
        return null;
    }

    private Set<Produto> toResourceProdutos(List<Document> documents) {
        Set<Produto> produtos = null;
        if (documents == null) {
            return produtos;
        }
        ProdutoResourceAssembly assembly = new ProdutoResourceAssembly();
        produtos = new HashSet<Produto>();
        for(Document document : documents) {
            produtos.add(assembly.toResource(document));
        }

        return produtos;
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

    private Set<Endereco> toResourceEnderecos(List<Document> documents) {
        Set<Endereco> resources = null;
        if (documents == null) {
            return resources;
        }
        resources = new HashSet<Endereco>();
        EnderecoResourceAssembly resource = new EnderecoResourceAssembly();
        for(Document document : documents) {
            resources.add(resource.toResource(document));
        }
        return resources;
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

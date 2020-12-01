package br.com.contmatic.assembly;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.joda.time.LocalDate;

import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

public class FuncionarioResourceAssembly implements Assembly<Funcionario, Document> {

    @Override
    public Funcionario toResource(Document document) {
        if (document != null) {
            Funcionario resource = new Funcionario();
            resource.setCpf(document.getString("cpf"));
            resource.setNome(document.getString("nome"));
            Integer idade = validarIdade(document);
            resource.setIdade(idade);
            resource.setTelefones(toResourceTelefones(document.getList("telefones", Document.class)));
            resource.setEnderecos(toResourceEnderecos(document.getList("enderecos", Document.class)));
            BigDecimal salarioBigDecimal = validarBoleto(document);
            resource.setSalario(salarioBigDecimal);
            resource.setDataContratacao(LocalDate(document.getString("dataContratacao")));
            resource.setDataSalario(LocalDate(document.getString("dataSalario")));
            return resource;
        }
        return null;
    }

    private static BigDecimal validarBoleto(Document document) {
        if (document.getDouble("salario") != null) {
            Double salarioDouble = document.getDouble("salario");
            BigDecimal salarioBigDecimal = BigDecimal.valueOf(salarioDouble).setScale(2);
            return salarioBigDecimal;
        }
        return BigDecimal.valueOf(0);
    }

    private static Integer validarIdade(Document document) {
        if (document.getInteger("idade") != null) {
            Integer idade = document.getInteger("idade");
            return idade;
        }
        return 0;
    }

    @Override
    public Document toDocument(Funcionario resource) {
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

    private LocalDate LocalDate(String data) {
        return data == null ? null : LocalDate.parse(data);
    }

}

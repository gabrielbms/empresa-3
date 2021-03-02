package br.com.contmatic.easyRandom;

import java.math.BigDecimal;
import java.util.HashSet;

import org.jeasy.random.EasyRandom;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.github.javafaker.Faker;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Fornecedor;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.EstadoType;
import br.com.contmatic.regex.RegexType;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDDType;
import br.com.contmatic.telefone.TelefoneType;

public class EasyRandomClass {

    static Faker faker = new Faker();
    EasyRandom easyFakeObject = new EasyRandom();
    public static EasyRandomClass easy = new EasyRandomClass();

    public Cliente clienteRandomizer() {
        Cliente cliente = easyFakeObject.nextObject(Cliente.class);
        cliente.setCpf(Geradores.geraCpf());
        String nomeCliente = faker.name().fullName().replace(".", "");
        cliente.setNome(nomeCliente);
        cliente.setEmail(nomeCliente.toLowerCase().replace(" ", "").replace("'", "") + Geradores.DominioEmail());
        cliente.setTelefones(telefoneRandomizer());
        cliente.setBoleto(Geradores.generateRandomBigDecimalValueFromRange(BigDecimal.valueOf(10.00), BigDecimal.valueOf(500.00).setScale(2)));
        cliente.setDataCriacao(DateTime.now());
        cliente.setDataModificacao(DateTime.now());
        String nomeUsuario = faker.name().fullName().replace(".", "");
        cliente.setUsuarioCriacao(nomeUsuario);
        cliente.setUsuarioModificacao(nomeUsuario);
        return cliente;
    }

    public Empresa empresaRandomizer() {
        Empresa empresa = easyFakeObject.nextObject(Empresa.class);
        empresa.setCnpj(Geradores.geraCnpj());
        String nomeEmpresa = faker.company().name().replace(".", "");
        empresa.setNome(nomeEmpresa);
        empresa.setSite("www." + nomeEmpresa.toLowerCase().replace(" ", "").replace("'", "").replace(",", "").replace("-", "") + ".com.br");
        empresa.setTelefones(telefoneRandomizer());
        empresa.setEnderecos(enderecoRandomizer());
        empresa.setDataCriacao(DateTime.now());
        empresa.setDataModificacao(DateTime.now());
        String nomeUsuario = faker.name().fullName().replace(".", "");
        empresa.setUsuarioCriacao(nomeUsuario);
        empresa.setUsuarioModificacao(nomeUsuario);
        return empresa;
    }

    public Fornecedor fornecedorRandomizer() {
        Fornecedor fornecedor = easyFakeObject.nextObject(Fornecedor.class);
        fornecedor.setCnpj(Geradores.geraCnpj());
        String nomeFornecedor = faker.company().name().replace(".", "");
        fornecedor.setNome(nomeFornecedor);
        fornecedor.setProduto(produtoRandomizer());
        fornecedor.setTelefones(telefoneRandomizer());
        fornecedor.setEnderecos(enderecoRandomizer());
        fornecedor.setDataCriacao(DateTime.now());
        fornecedor.setDataModificacao(DateTime.now());
        String nomeUsuario = faker.name().fullName().replace(".", "");
        fornecedor.setUsuarioCriacao(nomeUsuario);
        fornecedor.setUsuarioModificacao(nomeUsuario);
        return fornecedor;
    }

    public Funcionario funcionarioRandomizer() {
        Funcionario funcionario = easyFakeObject.nextObject(Funcionario.class);
        funcionario.setCpf(Geradores.geraCpf());
        funcionario.setNome(faker.name().fullName().replace(".", ""));
        funcionario.setIdade(faker.number().numberBetween(18, 40));
        funcionario.setSalario(Geradores.generateRandomBigDecimalValueFromRange(BigDecimal.valueOf(1045.00), BigDecimal.valueOf(8000.00).setScale(2)));
        funcionario.setDataContratacao(new LocalDate(Geradores.geraData(new LocalDate(2012, 01, 01), new LocalDate(2014, 12, 30)).toString()));
        funcionario.setDataSalario(new LocalDate(Geradores.geraData(new LocalDate(2015, 01, 01), new LocalDate(2022, 12, 30)).toString()));
        funcionario.setTelefones(telefoneRandomizer());
        funcionario.setEnderecos(enderecoRandomizer());
        funcionario.setDataCriacao(DateTime.now());
        funcionario.setDataModificacao(DateTime.now());
        String nomeUsuario = faker.name().fullName().replace(".", "");
        funcionario.setUsuarioCriacao(nomeUsuario);
        funcionario.setUsuarioModificacao(nomeUsuario);
        return funcionario;
    }

    public HashSet<Produto> produtoRandomizer() {
        HashSet<Produto> produtos = new HashSet<Produto>();
        Produto produto = easyFakeObject.nextObject(Produto.class);
        produto.setId(faker.number().numberBetween(1, 10000));
        produto.setNome(faker.name().fullName().replace(".", ""));
        produto.setQuantidade(faker.number().numberBetween(1, 10));
        produto.setPreco(Geradores.generateRandomBigDecimalValueFromRange(BigDecimal.valueOf(5.00), BigDecimal.valueOf(300.00).setScale(2)));
        produto.setDataCriacao(DateTime.now());
        produto.setDataModificacao(DateTime.now());
        String nomeUsuario = faker.name().fullName().replace(".", "");
        produto.setUsuarioCriacao(nomeUsuario);
        produto.setUsuarioModificacao(nomeUsuario);
        produtos.add(produto);
        return produtos;
    }

    public Produto produtoRandomizerClass() {
        Produto produto = easyFakeObject.nextObject(Produto.class);
        produto.setId(faker.number().numberBetween(1, 10000));
        produto.setNome(faker.name().fullName());
        produto.setQuantidade(faker.number().numberBetween(1, 10));
        produto.setPreco(Geradores.generateRandomBigDecimalValueFromRange(BigDecimal.valueOf(5.00), BigDecimal.valueOf(300.00).setScale(2)));
        produto.setDataCriacao(DateTime.now());
        produto.setDataModificacao(DateTime.now());
        String nomeUsuario = faker.name().fullName().replace(".", "");
        produto.setUsuarioCriacao(nomeUsuario);
        produto.setUsuarioModificacao(nomeUsuario);
        return produto;
    }

    public HashSet<Endereco> enderecoRandomizer() {
        HashSet<Endereco> enderecos = new HashSet<Endereco>();
        Endereco endereco = easyFakeObject.nextObject(Endereco.class);
        endereco.setCep(faker.regexify("[0-9]{8}"));
        endereco.setRua(faker.address().streetName());
        endereco.setNumero(faker.number().numberBetween(1, 1000));
        endereco.setComplemento(faker.address().lastName());
        endereco.setBairro(faker.address().state());
        endereco.setCidade(faker.address().cityName());
        endereco.setEstado(EstadoType.estadoAleatorio());
        enderecos.add(endereco);
        return enderecos;
    }

    public Endereco enderecoRandomizerClass() {
        Faker faker = new Faker();
        Endereco endereco = easyFakeObject.nextObject(Endereco.class);
        endereco.setCep(faker.regexify("[0-9]{8}"));
        endereco.setRua(faker.address().streetName());
        endereco.setNumero(faker.number().randomDigit());
        endereco.setComplemento(faker.address().lastName());
        endereco.setBairro(faker.address().state());
        endereco.setCidade(faker.address().cityName());
        endereco.setEstado(EstadoType.estadoAleatorio());
        return endereco;
    }

    public HashSet<Telefone> telefoneRandomizer() {
        HashSet<Telefone> telefones = new HashSet<Telefone>();
        Telefone telefone = easyFakeObject.nextObject(Telefone.class);
        telefone.setDdd(TelefoneDDDType.dddAleatorio());
        telefone.setTipoTelefone(TelefoneType.tipoTelefoneAleatorio());
        if (telefone.getTipoTelefone().equals(TelefoneType.CELULAR)) {
            telefone.setNumero(faker.regexify(RegexType.NUMERO_CELULAR));
        } else {
            telefone.setNumero(faker.regexify(RegexType.NUMERO_FIXO));
        }
        telefones.add(telefone);
        return telefones;
    }

    public Telefone telefoneRandomizerClass() {
        Telefone telefone = easyFakeObject.nextObject(Telefone.class);
        telefone.setDdd(TelefoneDDDType.dddAleatorio());
        if (telefone.getTipoTelefone().equals(TelefoneType.CELULAR)) {
            telefone.setNumero(faker.regexify(RegexType.NUMERO_CELULAR));
        } else {
            telefone.setNumero(faker.regexify(RegexType.NUMERO_FIXO));
        }
        telefone.setTipoTelefone(TelefoneType.tipoTelefoneAleatorio());
        return telefone;
    }

    public static EasyRandomClass InstanciaEasyRandomClass() {
        EasyRandomClass easyRandom = new EasyRandomClass();
        return easyRandom;
    }
    
    public static String geraNomeUsuario() {
        String nome = faker.name().fullName().replace(".", "");
        return nome;
    }

}

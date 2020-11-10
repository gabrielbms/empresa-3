package br.com.contmatic.easyRandom;

import java.math.BigDecimal;
import java.util.HashSet;

import org.jeasy.random.EasyRandom;
import org.joda.time.LocalDate;

import com.github.javafaker.Faker;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Fornecedor;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.Estado;
import br.com.contmatic.regex.RegexType;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDD;
import br.com.contmatic.telefone.TipoTelefone;

public class EasyRandomClass {
	
    public static EasyRandomClass easy = new EasyRandomClass();
    
    public Cliente clienteRandomizer() {
        Faker faker = new Faker();
        EasyRandom easyFakeObject = new EasyRandom();
        Cliente cliente = easyFakeObject.nextObject(Cliente.class);
        cliente.setCpf(Geradores.geraCpf());
        String nomeCliente = faker.name().fullName();
        cliente.setNome(nomeCliente);
        cliente.setEmail(nomeCliente.toLowerCase().replace(" ", "").replace("'", "") + Geradores.DominioEmail());
        cliente.setTelefones(telefoneRandomizer());
        cliente.setBoleto(Geradores.generateRandomBigDecimalValueFromRange(
        		BigDecimal.valueOf(10.00), BigDecimal.valueOf(500.00).setScale(1)));
        return cliente;
    }
    
    public Empresa empresaRandomizer() {
        Faker faker = new Faker();
        EasyRandom easyFakeObject = new EasyRandom();
        Empresa empresa = easyFakeObject.nextObject(Empresa.class);
        empresa.setCnpj(Geradores.geraCnpj());
        String nomeEmpresa = faker.company().name();
        empresa.setNome(nomeEmpresa);
        empresa.setSite("www." + nomeEmpresa.toLowerCase().replace(" ", "").replace("'", "")
        		.replace(",", "").replace("-", "") + ".com.br");
        empresa.setTelefones(telefoneRandomizer());
        empresa.setEnderecos(enderecoRandomizer());	
        return empresa;
    }
    
    public Fornecedor fornecedorRandomizer() {
        Faker faker = new Faker();
        EasyRandom easyFakeObject = new EasyRandom();
        Fornecedor fornecedor = easyFakeObject.nextObject(Fornecedor.class);
        fornecedor.setCnpj(Geradores.geraCnpj());
        String nomeFornecedor = faker.company().name();
        fornecedor.setNome(nomeFornecedor);
        fornecedor.setProduto(faker.commerce().productName());
        fornecedor.setTelefones(telefoneRandomizer());
        fornecedor.setEnderecos(enderecoRandomizer());
        return fornecedor;
    }
    
    public Funcionario funcionarioRandomizer() {
        Faker faker = new Faker();
        EasyRandom easyFakeObject = new EasyRandom();
        Funcionario funcionario = easyFakeObject.nextObject(Funcionario.class);
        funcionario.setCpf(Geradores.geraCpf());
        funcionario.setNome(faker.name().fullName());
        funcionario.setIdade(faker.number().numberBetween(18, 40));
        funcionario.setSalario(Geradores.generateRandomBigDecimalValueFromRange(
        		BigDecimal.valueOf(1000.00), BigDecimal.valueOf(8000.00).setScale(2)));
        funcionario.setDataContratacao(new LocalDate(Geradores.geraData(
        		new LocalDate(2012, 01, 01), new LocalDate(2014, 12, 30)).toString()));
        funcionario.setDataSalario(new LocalDate(Geradores.geraData(
        		new LocalDate(2015, 01, 01), new LocalDate(2022, 12, 30)).toString()));
        funcionario.setTelefones(telefoneRandomizer());
        funcionario.setEnderecos(enderecoRandomizer());
        return funcionario;
    }
    
    public HashSet<Endereco> enderecoRandomizer() {
    	HashSet<Endereco> enderecos = new HashSet<Endereco>();
        Faker faker = new Faker();
        EasyRandom easyFakeObject = new EasyRandom();
        Endereco endereco = easyFakeObject.nextObject(Endereco.class);
        endereco.setCep(faker.regexify("[0-9]{8}"));
        endereco.setRua(faker.address().streetName());
        endereco.setNumero(faker.number().numberBetween(1, 1000));
        endereco.setComplemento(faker.address().lastName());
        endereco.setBairro(faker.address().state());
        endereco.setCidade(faker.address().cityName());
        endereco.setEstado(Estado.estadoAleatorio());
        enderecos.add(endereco);
        return enderecos;
    }
    
    public Endereco enderecoRandomizerClass() {
        Faker faker = new Faker();
        EasyRandom easyFakeObject = new EasyRandom();
        Endereco endereco = easyFakeObject.nextObject(Endereco.class);
        endereco.setCep(faker.regexify("[0-9]{8}"));
        endereco.setRua(faker.address().streetName());
        endereco.setNumero(faker.number().randomDigit());
        endereco.setComplemento(faker.address().lastName());
        endereco.setBairro(faker.address().state());
        endereco.setCidade(faker.address().cityName());
        endereco.setEstado(Estado.estadoAleatorio());
        return endereco;
    }
    
    public HashSet<Telefone> telefoneRandomizer() {
    	HashSet<Telefone> telefones = new HashSet<Telefone>();
        Faker faker = new Faker();
        EasyRandom easyFakeObject = new EasyRandom();
        Telefone telefone = easyFakeObject.nextObject(Telefone.class);
        telefone.setDdd(TelefoneDDD.DddAleatorio());
        telefone.setTipoTelefone(TipoTelefone.tipoTelefoneAleatorio());
        if (telefone.getTipoTelefone().equals(TipoTelefone.CELULAR)) {
        	 telefone.setNumero(faker.regexify(RegexType.NUMERO_CELULAR));
        } else {
        	 telefone.setNumero(faker.regexify(RegexType.NUMERO_FIXO));
        }
        telefones.add(telefone);
        return telefones;
    }
    
    public Telefone telefoneRandomizerClass() {
        Faker faker = new Faker();
        EasyRandom easyFakeObject = new EasyRandom();
        Telefone telefone = easyFakeObject.nextObject(Telefone.class);
        telefone.setDdd(TelefoneDDD.DddAleatorio());
        if (telefone.getTipoTelefone().equals(TipoTelefone.CELULAR)) {
       	 telefone.setNumero(faker.regexify(RegexType.NUMERO_CELULAR));
       } else {
       	 telefone.setNumero(faker.regexify(RegexType.NUMERO_FIXO));
       }
        telefone.setTipoTelefone(TipoTelefone.tipoTelefoneAleatorio());
        return telefone;
    }
    
    public static EasyRandomClass InstanciaEasyRandomClass() {
    	EasyRandomClass easyRandom = new EasyRandomClass();
        return easyRandom;
    }
    
}

package br.com.contmatic.util;

import java.math.BigDecimal;
import java.util.Random;

import org.joda.time.LocalDate;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Fornecedor;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.Estado;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDD;
import br.com.contmatic.telefone.TipoTelefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * A factory for creating EmpresaFixture objects.
 */
public class EmpresaFixtureFactory implements TemplateLoader {

    /**
     * Load.
     */
    @Override
    public void load() {
        Fixture.of(Cliente.class).addTemplate("valido", new Rule() {
            {
                add("cpf", random("33965510100", "01283513307", "92442158299", "81645412148"));
                add("nome", random("Gabriel", "Bueno", "Santos"));
                add("email", random("gabriel@hotmail.com", "bueno@gmail.com", "julia@bol.com.br"));
                add("telefones", has(1).of(Telefone.class, "valido"));
                add("boleto", BigDecimal.valueOf(200.00));
            }
        });

        Fixture.of(Empresa.class).addTemplate("valido", new Rule() {
            {
                add("cnpj", random("21298596000128", "71388515000159", "13934077000180"));
                add("nome", random("Gabriel", "Bueno", "Santos"));
                add("site", random("http://www.gbconsertos.com.br"));
                add("telefones", has(1).of(Telefone.class, "valido"));
                add("enderecos", has(1).of(Endereco.class, "valido"));
            }
        });

        Fixture.of(Fornecedor.class).addTemplate("valido", new Rule() {
            {
                add("cnpj", random("36621217000166", "59660254000189", "81398148000128"));
                add("nome", random("Gabriel", "Gustavo", "Santos"));
                add("telefones", has(1).of(Telefone.class, "valido"));
                add("produto", random("5 placas maes", "2 processador intel I3"));
                add("enderecos", has(1).of(Endereco.class, "valido"));
            }
        });

        Fixture.of(Funcionario.class).addTemplate("valido", new Rule() {
            {
                add("cpf", random("33965510100", "01283513307", "92442158299", "81645412148"));
                add("nome", random("Gabriel", "Gustavo", "Santos"));
                add("idade", random(20, 25, 30, 35, 40));
                add("telefones", has(1).of(Telefone.class, "valido"));
                add("enderecos", has(1).of(Endereco.class, "valido"));
                add("salario", BigDecimal.valueOf(2500.00));
                add("dataContratacao", random(new LocalDate(2019, 05, 05)));
                add("dataSalario", random(new LocalDate(2021, 05, 05), new LocalDate(2021, 06, 06), new LocalDate(2021, 07, 07)));

            }
        });

        Fixture.of(Endereco.class).addTemplate("valido", new Rule() {
            {
                add("cep", random("08121019", "04570050", "02243140", "02243140"));
                add("rua", random("Rua Padre Estevão Pernet", "Av Conselheiro Carrão"));
                add("numero", random(02, 05, 10, 15, 20));
                add("complemento", random("Apartamento", "SN"));
                add("bairro", random("Tatuapé", "Belém", "Carrão"));
                add("cidade", "São Paulo");
                add("estado", (Estado.values()[new Random().nextInt(27)]));
            }
        });

        Fixture.of(Telefone.class).addTemplate("valido", new Rule() {
            {
                add("ddd", (TelefoneDDD.values()[new Random().nextInt(66)]));
                add("numero", random("906257327", "918556091", "918556091", "68700598", "84792823", "32005976"));
                add("tipoTelefone", (TipoTelefone.values()[new Random().nextInt(2)]));
            }
        });

    }
}

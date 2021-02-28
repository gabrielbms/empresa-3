package br.com.contmatic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.empresa.Funcionario;

public class FuncionarioServiceTest {

    private FuncionarioService funcionarioService = new FuncionarioService();

    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FuncionarioServiceTest.class);
    
    @Test
    public void loop() {
        for(int i = 0 ; i < 10; i++) {
            deve_salvar_empresa();
        }
    }

    @Test
    public void deve_salvar_empresa() {
        try {
            Funcionario funcionario = randomObject.funcionarioRandomizer();
            funcionarioService.save(funcionario);
            assertEquals(funcionario, funcionarioService.findById(funcionario.getCpf()));
        } catch (Exception e) { 
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_atualizar_um_funcionario() {
        try {
            Funcionario funcionario = randomObject.funcionarioRandomizer();
            funcionarioService.save(funcionario);
            funcionario.setNome("Atualizando um funcionario");
            funcionarioService.update(funcionario);
            Funcionario funcionarioBanco = funcionarioService.findById(funcionario.getCpf());
            assertEquals("Atualizando um funcionario", funcionarioBanco.getNome());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_atualizar_um_funcionario_pelo_campo() {
        try {
            Funcionario funcionario = randomObject.funcionarioRandomizer();
            String nomefuncionario = funcionario.getNome();
            funcionarioService.save(funcionario);
            funcionario.setNome("Atualizando um funcionario pelo campo");
            funcionarioService.updateByField("nome", nomefuncionario, funcionario);
            Funcionario funcionarioBanco = funcionarioService.findById(funcionario.getCpf());
            assertEquals("Atualizando um funcionario pelo campo", funcionarioBanco.getNome());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_deletar_um_funcionario_pelo_cpf() {
        try {
            Funcionario funcionario = randomObject.funcionarioRandomizer();
            funcionarioService.save(funcionario);
            assertEquals(funcionario, funcionarioService.findById(funcionario.getCpf()));
            funcionarioService.deleteById(funcionario.getCpf());
            Funcionario funcionarioDeletado = funcionarioService.findById(funcionario.getCpf());
            assertEquals(null, funcionarioDeletado.getCpf());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_deletar_um_funcionario_pelo_campo() {
        try {
            Funcionario funcionario = randomObject.funcionarioRandomizer();
            String nome = funcionario.getNome();
            funcionarioService.save(funcionario);
            assertEquals(funcionario, funcionarioService.findById(funcionario.getCpf()));
            funcionarioService.deleteByField("nome", nome, funcionario);
            Funcionario funcionarioDeletado = funcionarioService.findById(funcionario.getCpf());
            assertEquals(null, funcionarioDeletado.getCpf());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecioar_um_funcionario_pelo_cpf() {
        try {
            Funcionario funcionario = randomObject.funcionarioRandomizer();
            funcionarioService.save(funcionario);
            Funcionario funcionarioBanco = funcionarioService.findById(funcionario.getCpf());
            assertEquals(funcionario, funcionarioBanco);
            funcionarioService.deleteById(funcionario.getCpf());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecionar_por_nome() {
        try {
            Funcionario funcionario = randomObject.funcionarioRandomizer();
            funcionarioService.save(funcionario);
            List<String> campos = new ArrayList<>();
            Collections.addAll(campos, "nome");
            Funcionario funcionarioBuscado = funcionarioService.findAndReturnsSelectedFields("cpf", funcionario.getCpf(), campos);
            assertEquals(funcionario.getNome(), funcionarioBuscado.getNome());
            funcionarioService.deleteById(funcionario.getCpf());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecionar_por_cpf_nome() {
        try {
            Funcionario funcionario = randomObject.funcionarioRandomizer();
            funcionarioService.save(funcionario);
            List<String> campos = new ArrayList<>();
            Collections.addAll(campos, "cpf", "nome");
            Funcionario funcionarioBuscado = funcionarioService.findAndReturnsSelectedFields("cpf", funcionario.getCpf(), campos);
            assertEquals(funcionario.getNome(), funcionarioBuscado.getNome());
            funcionarioService.deleteById(funcionario.getCpf());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecionar_por_cpf_nome_idade_salario_dataContratacao_dataSalario_telefones_enderecos() {
        try {
            Funcionario funcionario = randomObject.funcionarioRandomizer();
            funcionarioService.save(funcionario);
            List<String> campos = new ArrayList<>();
            Collections.addAll(campos, "cpf", "nome", "idade", "salario", "dataContratacao", "dataSalario", "telefones", "enderecos");
            Funcionario funcionarioBuscado = funcionarioService.findAndReturnsSelectedFields("cpf", funcionario.getCpf(), campos);
            assertEquals(funcionario.getNome(), funcionarioBuscado.getNome());
            funcionarioService.deleteById(funcionario.getCpf());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecioar_todos_os_funcionarios() {
        try {
            List<Funcionario> funcionariosBanco = funcionarioService.findAll();
            assertNotNull(funcionariosBanco);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}

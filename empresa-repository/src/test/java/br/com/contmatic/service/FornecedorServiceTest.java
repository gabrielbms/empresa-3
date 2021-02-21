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
import br.com.contmatic.empresa.Fornecedor;

public class FornecedorServiceTest {

    private FornecedorService fornecedorService = new FornecedorService();

    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FornecedorServiceTest.class);

    @Test
    public void deve_salvar_fornecedor() {
        try {
            Fornecedor fornecedor = randomObject.fornecedorRandomizer();
            fornecedorService.save(fornecedor);
            assertEquals(fornecedor, fornecedorService.findById(fornecedor.getCnpj()));
        } catch (Exception e) { 
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_atualizar_um_cliente() {
        try {
            Fornecedor fornecedor = randomObject.fornecedorRandomizer();
            fornecedorService.save(fornecedor);
            fornecedor.setNome("Atualizando um fornecedor");
            fornecedorService.update(fornecedor);
            Fornecedor fornecedorBanco = fornecedorService.findById(fornecedor.getCnpj());
            assertEquals("Atualizando um fornecedor", fornecedorBanco.getNome());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_atualizar_uma_empresa_pelo_campo() {
        try {
            Fornecedor fornecedor = randomObject.fornecedorRandomizer();
            String nomefornecedor = fornecedor.getNome();
            fornecedorService.save(fornecedor);
            fornecedor.setNome("Atualizando um fornecedor pelo campo");
            fornecedorService.updateByField("nome", nomefornecedor, fornecedor);
            Fornecedor fornecedorBanco = fornecedorService.findById(fornecedor.getCnpj());
            assertEquals("Atualizando um fornecedor pelo campo", fornecedorBanco.getNome());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_deletar_um_cliente_pelo_cpf() {
        try {
            Fornecedor fornecedor = randomObject.fornecedorRandomizer();
            fornecedorService.save(fornecedor);
            assertEquals(fornecedor, fornecedorService.findById(fornecedor.getCnpj()));
            fornecedorService.deleteById(fornecedor.getCnpj());
            Fornecedor clienteDeletado = fornecedorService.findById(fornecedor.getCnpj());
            assertEquals(null, clienteDeletado.getCnpj());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_deletar_um_empresa_pelo_campo() {
        try {
            Fornecedor fornecedor = randomObject.fornecedorRandomizer();
            String nome = fornecedor.getNome();
            fornecedorService.save(fornecedor);
            assertEquals(fornecedor, fornecedorService.findById(fornecedor.getCnpj()));
            fornecedorService.deleteByField("nome", nome, fornecedor);
            Fornecedor fornecedorDeletado = fornecedorService.findById(fornecedor.getCnpj());
            assertEquals(null, fornecedorDeletado.getCnpj());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecioar_um_cliente_pelo_cpf() {
        try {
            Fornecedor fornecedor = randomObject.fornecedorRandomizer();
            fornecedorService.save(fornecedor);
            Fornecedor fornecedorBanco = fornecedorService.findById(fornecedor.getCnpj());
            assertEquals(fornecedor, fornecedorBanco);
            fornecedorService.deleteById(fornecedor.getCnpj());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecionar_por_campos() {
        try {
            Fornecedor fornecedor = randomObject.fornecedorRandomizer();
            fornecedorService.save(fornecedor);
            List<String> campos = new ArrayList<>();
            Collections.addAll(campos, "cnpj", "nome", "produtos", "enderecos", "telefones");
            Fornecedor fornecedorBuscado = fornecedorService.findAndReturnsSelectedFields("cnpj", fornecedor.getCnpj(), campos);
            assertEquals(fornecedor.getNome(), fornecedorBuscado.getNome());
            fornecedorService.deleteById(fornecedor.getCnpj());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecioar_todos_os_clientes() {
        try {
            List<Fornecedor> fornecedorsBanco = fornecedorService.findAll();
            assertNotNull(fornecedorsBanco);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}

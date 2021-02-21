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
import br.com.contmatic.empresa.Empresa;

public class EmpresaServiceTest {

    private EmpresaService empresaService = new EmpresaService();

    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EmpresaServiceTest.class);
    
    @Test
    public void deve_salvar_empresa() {
        try {
            Empresa empresa = randomObject.empresaRandomizer();
            empresaService.save(empresa);
            assertEquals(empresa, empresaService.findById(empresa.getCnpj()));
        } catch (Exception e) { 
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_atualizar_uma_empresa() {
        try {
            Empresa empresa = randomObject.empresaRandomizer();
            empresaService.save(empresa);
            empresa.setNome("Atualizando uma empresa");
            empresaService.update(empresa);
            Empresa empresaBanco = empresaService.findById(empresa.getCnpj());
            assertEquals("Atualizando uma empresa", empresaBanco.getNome());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_atualizar_uma_empresa_pelo_campo() {
        try {
            Empresa empresa = randomObject.empresaRandomizer();
            String nomeempresa = empresa.getNome();
            empresaService.save(empresa);
            empresa.setNome("Atualizando um empresa pelo campo");
            empresaService.updateByField("nome", nomeempresa, empresa);
            Empresa empresaBanco = empresaService.findById(empresa.getCnpj());
            assertEquals("Atualizando um empresa pelo campo", empresaBanco.getNome());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_deletar_uma_empresa_pelo_cpf() {
        try {
            Empresa empresa = randomObject.empresaRandomizer();
            empresaService.save(empresa);
            assertEquals(empresa, empresaService.findById(empresa.getCnpj()));
            empresaService.deleteById(empresa.getCnpj());
            Empresa empresaDeletado = empresaService.findById(empresa.getCnpj());
            assertEquals(null, empresaDeletado.getCnpj());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_deletar_um_empresa_pelo_campo() {
        try {
            Empresa empresa = randomObject.empresaRandomizer();
            String nome = empresa.getNome();
            empresaService.save(empresa);
            assertEquals(empresa, empresaService.findById(empresa.getCnpj()));
            empresaService.deleteByField("nome", nome, empresa);
            Empresa empresaDeletado = empresaService.findById(empresa.getCnpj());
            assertEquals(null, empresaDeletado.getCnpj());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecioar_uma_empresa_pelo_cpf() {
        try {
            Empresa empresa = randomObject.empresaRandomizer();
            empresaService.save(empresa);
            Empresa empresaBanco = empresaService.findById(empresa.getCnpj());
            assertEquals(empresa, empresaBanco);
            empresaService.deleteById(empresa.getCnpj());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecionar_por_campos() {
        try {
            Empresa empresa = randomObject.empresaRandomizer();
            empresaService.save(empresa);
            List<String> campos = new ArrayList<>();
            Collections.addAll(campos, "cnpj", "nome", "site", "telefones", "enderecos");
            Empresa empresaBuscado = empresaService.findAndReturnsSelectedFields("cnpj", empresa.getCnpj(), campos);
            assertEquals(empresa.getNome(), empresaBuscado.getNome());
            empresaService.deleteById(empresa.getCnpj());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecioar_todas_as_empresas() {
        try {
            List<Empresa> empresasBanco = empresaService.findAll();
            assertNotNull(empresasBanco);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}

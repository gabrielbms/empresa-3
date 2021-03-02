package br.com.contmatic.service;

import static br.com.contmatic.easyRandom.EasyRandomClass.geraNomeUsuario;
import static org.joda.time.DateTime.now;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.empresa.Produto;

public class ProdutoServiceTest {
	
    private ProdutoService produtoService = new ProdutoService();

    private static EasyRandomClass randomObject = EasyRandomClass.InstanciaEasyRandomClass();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoServiceTest.class);

    @Test
    public void deve_salvar_empresa() {
        try {
            Produto produto = randomObject.produtoRandomizerClass();
            produtoService.save(produto);
            assertEquals(produto, produtoService.findById(produto.getId()));
        } catch (Exception e) { 
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_atualizar_um_produto() {
        try {
            Produto produto = randomObject.produtoRandomizerClass();
            produtoService.save(produto);
            produto.setNome("Atualizando um produto");
            produto.setDataModificacao(now());
            produto.setUsuarioModificacao(geraNomeUsuario());
            produtoService.update(produto);
            Produto produtoBanco = produtoService.findById(produto.getId());
            assertEquals("Atualizando um produto", produtoBanco.getNome());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_atualizar_um_produto_pelo_campo() {
        try {
            Produto produto = randomObject.produtoRandomizerClass();
            String nomeproduto = produto.getNome();
            produtoService.save(produto);
            produto.setNome("Atualizando um produto pelo campo");
            produto.setDataModificacao(now());
            produto.setUsuarioModificacao(geraNomeUsuario());
            produtoService.updateByField("nome", nomeproduto, produto);
            Produto produtoBanco = produtoService.findById(produto.getId());
            assertEquals("Atualizando um produto pelo campo", produtoBanco.getNome());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_deletar_um_produto_pelo_cpf() {
        try {
            Produto produto = randomObject.produtoRandomizerClass();
            produtoService.save(produto);
            assertEquals(produto, produtoService.findById(produto.getId()));
            produtoService.deleteById(produto.getId());
            Produto produtoDeletado = produtoService.findById(produto.getId());
            assertEquals(null, produtoDeletado.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_deletar_um_produto_pelo_campo() {
        try {
            Produto produto = randomObject.produtoRandomizerClass();
            String nome = produto.getNome();
            produtoService.save(produto);
            assertEquals(produto, produtoService.findById(produto.getId()));
            produtoService.deleteByField("nome", nome, produto);
            Produto produtoDeletado = produtoService.findById(produto.getId());
            assertEquals(null, produtoDeletado.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecioar_um_produto_pelo_cpf() {
        try {
            Produto produto = randomObject.produtoRandomizerClass();
            produtoService.save(produto);
            Produto produtoBanco = produtoService.findById(produto.getId());
            assertEquals(produto, produtoBanco);
            produtoService.deleteById(produto.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecionar_por_nome() {
        try {
            Produto produto = randomObject.produtoRandomizerClass();
            produtoService.save(produto);
            List<String> campos = new ArrayList<>();
            Collections.addAll(campos, "nome");
            Produto produtoBuscado = produtoService.findAndReturnsSelectedFields("id", produto.getId(), campos);
            assertEquals(produto.getNome(), produtoBuscado.getNome());
            produtoService.deleteById(produto.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecionar_por_id_nome() {
        try {
            Produto produto = randomObject.produtoRandomizerClass();
            produtoService.save(produto);
            List<String> campos = new ArrayList<>();
            Collections.addAll(campos, "id", "nome");
            Produto produtoBuscado = produtoService.findAndReturnsSelectedFields("id", produto.getId(), campos);
            assertEquals(produto.getNome(), produtoBuscado.getNome());
            produtoService.deleteById(produto.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecionar_por_id_nome_quantidade_preco_dataCriacao() {
        try {
            Produto produto = randomObject.produtoRandomizerClass();
            produtoService.save(produto);
            List<String> campos = new ArrayList<>();
            Collections.addAll(campos, "id", "nome", "quantidade", "preco", "dataCriacao");
            Produto produtoBuscado = produtoService.findAndReturnsSelectedFields("id", produto.getId(), campos);
            assertEquals(produto.getNome(), produtoBuscado.getNome());
            produtoService.deleteById(produto.getId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void deve_selecioar_todos_os_produtos() {
        try {
            List<Produto> produtosBanco = produtoService.findAll();
            assertNotNull(produtosBanco);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}

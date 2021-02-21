package br.com.contmatic.repository;

import java.util.List;

import br.com.contmatic.empresa.Produto;

public interface ProdutoRepository {

    public String save(Produto produto);

    public void update(Produto produto);
    
    public void updateByField(String campo, String conteudo, Produto produto);
    
    public void deleteById(Integer id);
    
    public void deleteByField(String campo, String conteudo, Produto produto);

    public Produto findById(Integer id);
    
    public Produto findAndReturnsSelectedFields(String campo, Integer conteudoCampo, List<String> conteudo);

    public List<Produto> findAll();
    
}
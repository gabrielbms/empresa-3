package br.com.contmatic.repository;

import java.util.List;

import br.com.contmatic.empresa.Produto;

public interface ProdutoRepository {
	
	public void save(Produto produto) throws IllegalAccessException;

    public void update(Produto produto);

    public void deleteById(int id) throws IllegalAccessException;

    public Produto findById(int id);

    public List<Produto> findAll();
}

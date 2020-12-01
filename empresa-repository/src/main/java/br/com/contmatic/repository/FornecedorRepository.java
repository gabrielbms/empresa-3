package br.com.contmatic.repository;

import java.util.List;

import br.com.contmatic.empresa.Fornecedor;

public interface FornecedorRepository {

    public void save(Fornecedor fornecedor) throws IllegalAccessException;

    public void update(Fornecedor fornecedor);

    public void deleteById(int id) throws IllegalAccessException;

    public Fornecedor findById(int id);

    public List<Fornecedor> findAll();
}

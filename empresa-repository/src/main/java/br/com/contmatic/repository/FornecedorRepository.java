package br.com.contmatic.repository;

import java.util.List;

import br.com.contmatic.empresa.Fornecedor;

public interface FornecedorRepository {

    public String save(Fornecedor fornecedor);

    public void update(Fornecedor fornecedor);
    
    public void updateByField(String campo, String conteudo, Fornecedor fornecedor);
    
    public void deleteById(String cpf);
    
    public void deleteByField(String campo, String conteudo, Fornecedor fornecedor);

    public Fornecedor findById(String id);
    
    public Fornecedor findAndReturnsSelectedFields(String campo, String conteudoCampo, List<String> conteudo);

    public List<Fornecedor> findAll();
    
}
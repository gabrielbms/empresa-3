package br.com.contmatic.repository;

import java.util.List;

import br.com.contmatic.empresa.Cliente;

public interface ClienteRepository {

    public String save(Cliente cliente);

    public void update(Cliente cliente);
    
    public void updateByField(String campo, String conteudo, Cliente cliente);
    
    public void deleteById(String cpf);
    
    public void deleteByField(String campo, String conteudo, Cliente cliente);

    public Cliente findById(String id);
    
    public Cliente findAndReturnsSelectedFields(String campo, String conteudoCampo, List<String> conteudo);

    public List<Cliente> findAll();

}

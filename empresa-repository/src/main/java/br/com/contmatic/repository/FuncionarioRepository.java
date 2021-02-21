package br.com.contmatic.repository;

import java.util.List;

import br.com.contmatic.empresa.Funcionario;

public interface FuncionarioRepository {

    public String save(Funcionario funcionario);

    public void update(Funcionario funcionario);
    
    public void updateByField(String campo, String conteudo, Funcionario funcionario);
    
    public void deleteById(String cpf);
    
    public void deleteByField(String campo, String conteudo, Funcionario funcionario);

    public Funcionario findById(String id);
    
    public Funcionario findAndReturnsSelectedFields(String campo, String conteudoCampo, List<String> conteudo);

    public List<Funcionario> findAll();
}

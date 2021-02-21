package br.com.contmatic.repository;

import java.util.List;

import br.com.contmatic.empresa.Empresa;

public interface EmpresaRepository {

    public String save(Empresa empresa);

    public void update(Empresa empresa);
    
    public void updateByField(String campo, String conteudo, Empresa empresa);
    
    public void deleteById(String cpf);
    
    public void deleteByField(String campo, String conteudo, Empresa empresa);

    public Empresa findById(String id);
    
    public Empresa findAndReturnsSelectedFields(String campo, String conteudoCampo, List<String> conteudo);

    public List<Empresa> findAll();

}

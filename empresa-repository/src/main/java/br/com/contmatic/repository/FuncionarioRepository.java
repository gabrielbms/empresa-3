package br.com.contmatic.repository;

import java.util.List;

import br.com.contmatic.empresa.Funcionario;

public interface FuncionarioRepository {

    public void save(Funcionario cadastro) throws IllegalAccessException;

    public void update(Funcionario cadastro);

    public void deleteById(int id) throws IllegalAccessException;

    public Funcionario findById(int id);

    public List<Funcionario> findAll();
}

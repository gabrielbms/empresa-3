package br.com.contmatic.repository;

import java.util.List;

import br.com.contmatic.empresa.Cliente;

public interface ClienteRepository {

    public void save(Cliente cliente) throws IllegalAccessException;

    public void update(Cliente cliente);

    public void deleteById(int id) throws IllegalAccessException;

    public Cliente findById(int id);

    public List<Cliente> findAll();
}

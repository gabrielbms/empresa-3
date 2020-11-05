package br.com.contmatic.repository;

import java.util.List;

import br.com.contmatic.empresa.Empresa;

public interface EmpresaRepository {

	public void save(Empresa empresa) throws IllegalAccessException;

	public void update(Empresa empresa);

	public void deleteById(String cnpj) throws IllegalAccessException;

	public Empresa findById(String cnpj);

	public List<Empresa> findAll();

}
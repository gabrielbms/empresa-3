package br.com.contmatic.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.contmatic.assembly.ClienteResourceAssemblyTest;
import br.com.contmatic.assembly.EmpresaResourceAssemblyTest;
import br.com.contmatic.assembly.EnderecoResourceAssemblyTest;
import br.com.contmatic.assembly.FornecedorResourceAssemblyTest;
import br.com.contmatic.assembly.FuncionarioResourceAssemblyTest;
import br.com.contmatic.assembly.TelefoneResourceAssemblyTest;

/**
 * The Class TestRunner.
 */
@RunWith(Suite.class)
@SuiteClasses({ ClienteResourceAssemblyTest.class, EmpresaResourceAssemblyTest.class, FornecedorResourceAssemblyTest.class, 
	FuncionarioResourceAssemblyTest.class, EnderecoResourceAssemblyTest.class, TelefoneResourceAssemblyTest.class })
public class TestRunnerRepository {

}

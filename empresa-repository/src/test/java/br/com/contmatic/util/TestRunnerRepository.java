package br.com.contmatic.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.contmatic.assembly.ClienteResourceAssemblyTest;
import br.com.contmatic.assembly.EmpresaResourceAssemblyTest;
import br.com.contmatic.assembly.EnderecoResourceAssemblyTest;
import br.com.contmatic.assembly.FornecedorResourceAssemblyTest;
import br.com.contmatic.assembly.FuncionarioResourceAssemblyTest;
import br.com.contmatic.assembly.ProdutoResourceAssemblyTest;
import br.com.contmatic.assembly.TelefoneResourceAssemblyTest;
import br.com.contmatic.service.ClienteServiceTest;
import br.com.contmatic.service.EmpresaServiceTest;
import br.com.contmatic.service.FornecedorServiceTest;
import br.com.contmatic.service.FuncionarioServiceTest;
import br.com.contmatic.service.ProdutoServiceTest;

/**
 * The Class TestRunner.
 */
@RunWith(Suite.class)
@SuiteClasses({ ClienteResourceAssemblyTest.class, EmpresaResourceAssemblyTest.class, FornecedorResourceAssemblyTest.class,
    FuncionarioResourceAssemblyTest.class, EnderecoResourceAssemblyTest.class, TelefoneResourceAssemblyTest.class, 
    ProdutoResourceAssemblyTest.class, ClienteServiceTest.class, EmpresaServiceTest.class, FornecedorServiceTest.class, 
    FuncionarioServiceTest.class,
    ProdutoServiceTest.class })
public class TestRunnerRepository {

}

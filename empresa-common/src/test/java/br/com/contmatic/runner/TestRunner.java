package br.com.contmatic.runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.contmatic.util.TestRunnerModel;
import br.com.contmatic.util.TestRunnerRepository;

@RunWith(Suite.class)
@SuiteClasses({ TestRunnerModel.class, TestRunnerRepository.class })
public class TestRunner {

}

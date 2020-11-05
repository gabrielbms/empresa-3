package br.com.contmatic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.contmatic.easyRandom.EasyRandomClass;
import br.com.contmatic.empresa.Cliente;

public class ClienteServiceTest {
	
	private static ClienteService clienteService;

	private static Cliente cliente;

	private static EasyRandomClass random = EasyRandomClass.InstanciaEasyRandomClass();

	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteServiceTest.class);

	@BeforeClass
	public static void easyRandomDados() {
		clienteService = new ClienteService();
		cliente = random.clienteRandomizer();
		clienteService.save(cliente);
	}

	@Test
	public void deve_salvar_Cliente() {
		try {
			clienteService.save(cliente);
			assertEquals(cliente, clienteService.findById(cliente.getCpf()));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Test
	public void deve_atualizar_Cliente() {
		cliente.setNome("Teste");
		clienteService.update(cliente);
		assertEquals(clienteService.findById(cliente.getCpf()).getNome(), cliente.getNome());
	}

	@Test
	public void deve_deletar_Cliente() {
		try {
			EasyRandomClass random = EasyRandomClass.InstanciaEasyRandomClass();
			cliente = random.clienteRandomizer();
			clienteService.save(cliente);
			clienteService.deleteById(cliente.getCpf());
			assertEquals(null, clienteService.findById(cliente.getCpf()));
			clienteService.save(cliente);
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Test
	public void deve_retornar_todos_Clientes() {
		assertFalse(clienteService.findAll().isEmpty());
	}

}

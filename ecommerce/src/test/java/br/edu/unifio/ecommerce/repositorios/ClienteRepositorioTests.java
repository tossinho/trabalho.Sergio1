package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Cliente;

@SpringBootTest
public class ClienteRepositorioTests {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    public void deveInserirUmCliente() {
        Cliente cliente = new Cliente();

        cliente.setNome("Joao da Silva");
        cliente.setEmail("joao@email.com");
        cliente.setTelefone("14999999999");

        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        assertNotNull(clienteSalvo);
        assertNotNull(clienteSalvo.getId());
        assertEquals("Joao da Silva", clienteSalvo.getNome());
        assertEquals("joao@email.com", clienteSalvo.getEmail());
    }

    @Test
    public void deveBuscarUmClientePorId() {
        Cliente cliente = new Cliente();

        cliente.setNome("Maria Silva");
        cliente.setEmail("maria@email.com");
        cliente.setTelefone("14988888888");

        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        Cliente clienteEncontrado = clienteRepositorio
                .findById(clienteSalvo.getId())
                .orElseThrow();

        assertNotNull(clienteEncontrado);
        assertEquals("Maria Silva", clienteEncontrado.getNome());
        assertEquals("maria@email.com", clienteEncontrado.getEmail());
    }

    @Test
    public void deveListarClientes() {
        int quantidadeAntes = clienteRepositorio.findAll().size();

        Cliente cliente1 = new Cliente();
        cliente1.setNome("Carlos Silva");
        cliente1.setEmail("carlos@email.com");
        cliente1.setTelefone("14977777777");

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Ana Silva");
        cliente2.setEmail("ana@email.com");
        cliente2.setTelefone("14966666666");

        clienteRepositorio.save(cliente1);
        clienteRepositorio.save(cliente2);

        var clientes = clienteRepositorio.findAll();

        assertNotNull(clientes);
        assertEquals(quantidadeAntes + 2, clientes.size());
    }

    @Test
    public void deveAlterarUmCliente() {
        Cliente cliente = new Cliente();

        cliente.setNome("Pedro Silva");
        cliente.setEmail("pedro@email.com");
        cliente.setTelefone("14955555555");

        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        clienteSalvo.setNome("Pedro Silva Atualizado");

        clienteRepositorio.save(clienteSalvo);

        Cliente clienteAlterado = clienteRepositorio
                .findById(clienteSalvo.getId())
                .orElseThrow();

        assertEquals("Pedro Silva Atualizado", clienteAlterado.getNome());
        assertEquals("pedro@email.com", clienteAlterado.getEmail());
    }

    @Test
    public void deveExcluirUmCliente() {
        Cliente cliente = new Cliente();

        cliente.setNome("Cliente Para Excluir");
        cliente.setEmail("excluir@email.com");
        cliente.setTelefone("14944444444");

        Cliente clienteSalvo = clienteRepositorio.save(cliente);

        assertNotNull(
                clienteRepositorio.findById(clienteSalvo.getId()).orElse(null)
        );

        clienteRepositorio.deleteById(clienteSalvo.getId());

        assertEquals(false, clienteRepositorio.existsById(clienteSalvo.getId()));
    }
}
package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
public class PedidoRepositorioTests {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    public void deveInserirUmPedido() {
        Pedido pedido = new Pedido();

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("500.00"));
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        assertNotNull(pedidoSalvo);
        assertNotNull(pedidoSalvo.getId());
        assertEquals("ABERTO", pedidoSalvo.getStatus());
        assertEquals(new BigDecimal("500.00"), pedidoSalvo.getValorTotal());
        assertEquals(cliente.getId(), pedidoSalvo.getCliente().getId());
    }

    @Test
    public void deveBuscarUmPedidoPorId() {
        Pedido pedido = new Pedido();

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        pedido.setData(LocalDateTime.now());
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("300.00"));
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        Pedido pedidoEncontrado = pedidoRepositorio
                .findById(pedidoSalvo.getId())
                .orElseThrow();

        assertNotNull(pedidoEncontrado);
        assertEquals("PENDENTE", pedidoEncontrado.getStatus());
        assertEquals(new BigDecimal("300.00"), pedidoEncontrado.getValorTotal());
        assertEquals(cliente.getId(), pedidoEncontrado.getCliente().getId());
    }

    @Test
    public void deveListarPedidos() {
        int quantidadeAntes = pedidoRepositorio.findAll().size();

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido pedido1 = new Pedido();
        pedido1.setData(LocalDateTime.now());
        pedido1.setStatus("ABERTO");
        pedido1.setValorTotal(new BigDecimal("200.00"));
        pedido1.setCliente(cliente);

        Pedido pedido2 = new Pedido();
        pedido2.setData(LocalDateTime.now());
        pedido2.setStatus("PAGO");
        pedido2.setValorTotal(new BigDecimal("400.00"));
        pedido2.setCliente(cliente);

        pedidoRepositorio.save(pedido1);
        pedidoRepositorio.save(pedido2);

        var pedidos = pedidoRepositorio.findAll();

        assertNotNull(pedidos);
        assertEquals(quantidadeAntes + 2, pedidos.size());
    }

    @Test
    public void deveAlterarUmPedido() {
        Pedido pedido = new Pedido();

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("600.00"));
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        pedidoSalvo.setStatus("PAGO");

        pedidoRepositorio.save(pedidoSalvo);

        Pedido pedidoAlterado = pedidoRepositorio
                .findById(pedidoSalvo.getId())
                .orElseThrow();

        assertEquals("PAGO", pedidoAlterado.getStatus());
        assertEquals(new BigDecimal("600.00"), pedidoAlterado.getValorTotal());
    }

    @Test
    public void deveExcluirUmPedido() {
        Pedido pedido = new Pedido();

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        pedido.setData(LocalDateTime.now());
        pedido.setStatus("CANCELADO");
        pedido.setValorTotal(new BigDecimal("100.00"));
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        assertNotNull(
                pedidoRepositorio.findById(pedidoSalvo.getId()).orElse(null)
        );

        pedidoRepositorio.deleteById(pedidoSalvo.getId());

        assertEquals(false, pedidoRepositorio.existsById(pedidoSalvo.getId()));
    }
}
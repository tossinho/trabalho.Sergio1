package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.Pagamento;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
public class PagamentoRepositorioTests {

    @Autowired
    private PagamentoRepositorio pagamentoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    public void deveInserirUmPagamento() {
        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("500.00"));
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("500.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("CARTAO");
        pagamento.setPedido(pedidoSalvo);

        Pagamento pagamentoSalvo = pagamentoRepositorio.save(pagamento);

        assertNotNull(pagamentoSalvo);
        assertNotNull(pagamentoSalvo.getId());
        assertEquals(new BigDecimal("500.00"), pagamentoSalvo.getValor());
        assertEquals("APROVADO", pagamentoSalvo.getStatus());
        assertEquals("CARTAO", pagamentoSalvo.getTipo());
        assertEquals(pedidoSalvo.getId(), pagamentoSalvo.getPedido().getId());
    }

    @Test
    public void deveBuscarUmPagamentoPorId() {
        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("300.00"));
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("300.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("PENDENTE");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedidoSalvo);

        Pagamento pagamentoSalvo = pagamentoRepositorio.save(pagamento);

        Pagamento pagamentoEncontrado = pagamentoRepositorio
                .findById(pagamentoSalvo.getId())
                .orElseThrow();

        assertNotNull(pagamentoEncontrado);
        assertEquals(new BigDecimal("300.00"), pagamentoEncontrado.getValor());
        assertEquals("PENDENTE", pagamentoEncontrado.getStatus());
        assertEquals("PIX", pagamentoEncontrado.getTipo());
        assertEquals(pedidoSalvo.getId(), pagamentoEncontrado.getPedido().getId());
    }

    @Test
    public void deveListarPagamentos() {
        int quantidadeAntes = pagamentoRepositorio.findAll().size();

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido pedido1 = new Pedido();
        pedido1.setData(LocalDateTime.now());
        pedido1.setStatus("ABERTO");
        pedido1.setValorTotal(new BigDecimal("200.00"));
        pedido1.setCliente(cliente);

        Pedido pedido2 = new Pedido();
        pedido2.setData(LocalDateTime.now());
        pedido2.setStatus("ABERTO");
        pedido2.setValorTotal(new BigDecimal("400.00"));
        pedido2.setCliente(cliente);

        Pedido pedidoSalvo1 = pedidoRepositorio.save(pedido1);
        Pedido pedidoSalvo2 = pedidoRepositorio.save(pedido2);

        Pagamento pagamento1 = new Pagamento();
        pagamento1.setValor(new BigDecimal("200.00"));
        pagamento1.setData(LocalDateTime.now());
        pagamento1.setStatus("APROVADO");
        pagamento1.setTipo("PIX");
        pagamento1.setPedido(pedidoSalvo1);

        Pagamento pagamento2 = new Pagamento();
        pagamento2.setValor(new BigDecimal("400.00"));
        pagamento2.setData(LocalDateTime.now());
        pagamento2.setStatus("APROVADO");
        pagamento2.setTipo("CARTAO");
        pagamento2.setPedido(pedidoSalvo2);

        pagamentoRepositorio.save(pagamento1);
        pagamentoRepositorio.save(pagamento2);

        var pagamentos = pagamentoRepositorio.findAll();

        assertNotNull(pagamentos);
        assertEquals(quantidadeAntes + 2, pagamentos.size());
    }

    @Test
    public void deveAlterarUmPagamento() {
        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("600.00"));
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("600.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("PENDENTE");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedidoSalvo);

        Pagamento pagamentoSalvo = pagamentoRepositorio.save(pagamento);

        pagamentoSalvo.setStatus("APROVADO");

        pagamentoRepositorio.save(pagamentoSalvo);

        Pagamento pagamentoAlterado = pagamentoRepositorio
                .findById(pagamentoSalvo.getId())
                .orElseThrow();

        assertEquals("APROVADO", pagamentoAlterado.getStatus());
        assertEquals("PIX", pagamentoAlterado.getTipo());
        assertEquals(new BigDecimal("600.00"), pagamentoAlterado.getValor());
    }

    @Test
    public void deveExcluirUmPagamento() {
        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("100.00"));
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("100.00"));
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus("CANCELADO");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedidoSalvo);

        Pagamento pagamentoSalvo = pagamentoRepositorio.save(pagamento);

        assertNotNull(
                pagamentoRepositorio.findById(pagamentoSalvo.getId()).orElse(null)
        );

        pagamentoRepositorio.deleteById(pagamentoSalvo.getId());

        assertEquals(
                false,
                pagamentoRepositorio.existsById(pagamentoSalvo.getId())
        );
    }
}
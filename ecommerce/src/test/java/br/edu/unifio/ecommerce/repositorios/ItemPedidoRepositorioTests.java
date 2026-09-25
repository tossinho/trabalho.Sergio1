package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.ItemPedido;
import br.edu.unifio.ecommerce.entidades.Pedido;
import br.edu.unifio.ecommerce.entidades.Produtos;

@SpringBootTest
public class ItemPedidoRepositorioTests {

    @Autowired
    private ItemPedidoRepositorio itemPedidoRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Test
    public void deveInserirUmItemPedido() {
        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();
        Produtos produto = produtoRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("500.00"));
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        ItemPedido item = new ItemPedido();

        item.setQuantidade(2);
        item.setValorUnitario(new BigDecimal("100.00"));
        item.setPedido(pedidoSalvo);
        item.setProduto(produto);

        ItemPedido itemSalvo = itemPedidoRepositorio.save(item);

        assertNotNull(itemSalvo);
        assertNotNull(itemSalvo.getId());
        assertEquals(2, itemSalvo.getQuantidade());
        assertEquals(new BigDecimal("100.00"), itemSalvo.getValorUnitario());
        assertEquals(pedidoSalvo.getId(), itemSalvo.getPedido().getId());
        assertEquals(produto.getId(), itemSalvo.getProduto().getId());
    }

    @Test
    public void deveBuscarUmItemPedidoPorId() {
        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();
        Produtos produto = produtoRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("300.00"));
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        ItemPedido item = new ItemPedido();

        item.setQuantidade(3);
        item.setValorUnitario(new BigDecimal("50.00"));
        item.setPedido(pedidoSalvo);
        item.setProduto(produto);

        ItemPedido itemSalvo = itemPedidoRepositorio.save(item);

        ItemPedido itemEncontrado = itemPedidoRepositorio
                .findById(itemSalvo.getId())
                .orElseThrow();

        assertNotNull(itemEncontrado);
        assertEquals(3, itemEncontrado.getQuantidade());
        assertEquals(new BigDecimal("50.00"), itemEncontrado.getValorUnitario());
        assertEquals(pedidoSalvo.getId(), itemEncontrado.getPedido().getId());
        assertEquals(produto.getId(), itemEncontrado.getProduto().getId());
    }

    @Test
    public void deveListarItensPedido() {
        int quantidadeAntes = itemPedidoRepositorio.findAll().size();

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();
        Produtos produto = produtoRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("800.00"));
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        ItemPedido item1 = new ItemPedido();
        item1.setQuantidade(2);
        item1.setValorUnitario(new BigDecimal("100.00"));
        item1.setPedido(pedidoSalvo);
        item1.setProduto(produto);

        ItemPedido item2 = new ItemPedido();
        item2.setQuantidade(4);
        item2.setValorUnitario(new BigDecimal("200.00"));
        item2.setPedido(pedidoSalvo);
        item2.setProduto(produto);

        itemPedidoRepositorio.save(item1);
        itemPedidoRepositorio.save(item2);

        var itens = itemPedidoRepositorio.findAll();

        assertNotNull(itens);
        assertEquals(quantidadeAntes + 2, itens.size());
    }

    @Test
    public void deveAlterarUmItemPedido() {
        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();
        Produtos produto = produtoRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("600.00"));
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        ItemPedido item = new ItemPedido();

        item.setQuantidade(2);
        item.setValorUnitario(new BigDecimal("150.00"));
        item.setPedido(pedidoSalvo);
        item.setProduto(produto);

        ItemPedido itemSalvo = itemPedidoRepositorio.save(item);

        itemSalvo.setQuantidade(5);

        itemPedidoRepositorio.save(itemSalvo);

        ItemPedido itemAlterado = itemPedidoRepositorio
                .findById(itemSalvo.getId())
                .orElseThrow();

        assertEquals(5, itemAlterado.getQuantidade());
        assertEquals(new BigDecimal("150.00"), itemAlterado.getValorUnitario());
    }

    @Test
    public void deveExcluirUmItemPedido() {
        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();
        Produtos produto = produtoRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("ABERTO");
        pedido.setValorTotal(new BigDecimal("100.00"));
        pedido.setCliente(cliente);

        Pedido pedidoSalvo = pedidoRepositorio.save(pedido);

        ItemPedido item = new ItemPedido();

        item.setQuantidade(1);
        item.setValorUnitario(new BigDecimal("100.00"));
        item.setPedido(pedidoSalvo);
        item.setProduto(produto);

        ItemPedido itemSalvo = itemPedidoRepositorio.save(item);

        assertNotNull(
                itemPedidoRepositorio.findById(itemSalvo.getId()).orElse(null)
        );

        itemPedidoRepositorio.deleteById(itemSalvo.getId());

        assertEquals(
                false,
                itemPedidoRepositorio.existsById(itemSalvo.getId())
        );
    }
}
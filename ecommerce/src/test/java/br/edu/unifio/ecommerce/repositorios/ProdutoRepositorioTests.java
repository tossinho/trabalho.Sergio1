package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Categoria;
import br.edu.unifio.ecommerce.entidades.Produtos;

@SpringBootTest
public class ProdutoRepositorioTests {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    public void deveInserirUmProduto() {
        Produtos produto = new Produtos();

        Categoria categoria = categoriaRepositorio.findById((short) 1).orElseThrow();

        produto.setNome("Teclado Gamer");
        produto.setDescriçao("Teclado mecanico gamer");
        produto.setEstoque((short) 20);
        produto.setPreco(new BigDecimal("250.00"));
        produto.setCategoria(categoria);

        Produtos produtoSalvo = produtoRepositorio.save(produto);

        assertNotNull(produtoSalvo);
        assertNotNull(produtoSalvo.getId());
        assertEquals("Teclado Gamer", produtoSalvo.getNome());
        assertEquals(new BigDecimal("250.00"), produtoSalvo.getPreco());
        assertEquals(categoria.getId(), produtoSalvo.getCategoria().getId());
    }

    @Test
    public void deveBuscarUmProdutoPorId() {
        Produtos produto = new Produtos();

        Categoria categoria = categoriaRepositorio.findById((short) 1).orElseThrow();

        produto.setNome("Mouse Gamer");
        produto.setDescriçao("Mouse gamer");
        produto.setEstoque((short) 15);
        produto.setPreco(new BigDecimal("120.00"));
        produto.setCategoria(categoria);

        Produtos produtoSalvo = produtoRepositorio.save(produto);

        Produtos produtoEncontrado = produtoRepositorio
                .findById(produtoSalvo.getId())
                .orElseThrow();

        assertNotNull(produtoEncontrado);
        assertEquals("Mouse Gamer", produtoEncontrado.getNome());
        assertEquals(new BigDecimal("120.00"), produtoEncontrado.getPreco());
        assertEquals(categoria.getId(), produtoEncontrado.getCategoria().getId());
    }

    @Test
    public void deveListarProdutos() {
        int quantidadeAntes = produtoRepositorio.findAll().size();

        Categoria categoria = categoriaRepositorio.findById((short) 1).orElseThrow();

        Produtos produto1 = new Produtos();
        produto1.setNome("Monitor");
        produto1.setDescriçao("Monitor Full HD");
        produto1.setEstoque((short) 10);
        produto1.setPreco(new BigDecimal("800.00"));
        produto1.setCategoria(categoria);

        Produtos produto2 = new Produtos();
        produto2.setNome("Headset");
        produto2.setDescriçao("Headset gamer");
        produto2.setEstoque((short) 12);
        produto2.setPreco(new BigDecimal("300.00"));
        produto2.setCategoria(categoria);

        produtoRepositorio.save(produto1);
        produtoRepositorio.save(produto2);

        var produtos = produtoRepositorio.findAll();

        assertNotNull(produtos);
        assertEquals(quantidadeAntes + 2, produtos.size());
    }

    @Test
    public void deveAlterarUmProduto() {
        Produtos produto = new Produtos();

        Categoria categoria = categoriaRepositorio.findById((short) 1).orElseThrow();

        produto.setNome("Notebook");
        produto.setDescriçao("Notebook para estudos");
        produto.setEstoque((short) 5);
        produto.setPreco(new BigDecimal("3000.00"));
        produto.setCategoria(categoria);

        Produtos produtoSalvo = produtoRepositorio.save(produto);

        produtoSalvo.setNome("Notebook Atualizado");

        produtoRepositorio.save(produtoSalvo);

        Produtos produtoAlterado = produtoRepositorio
                .findById(produtoSalvo.getId())
                .orElseThrow();

        assertEquals("Notebook Atualizado", produtoAlterado.getNome());
        assertEquals(new BigDecimal("3000.00"), produtoAlterado.getPreco());
    }

    @Test
    public void deveExcluirUmProduto() {
        Produtos produto = new Produtos();

        Categoria categoria = categoriaRepositorio.findById((short) 1).orElseThrow();

        produto.setNome("Produto Para Excluir");
        produto.setDescriçao("Produto que será excluido");
        produto.setEstoque((short) 1);
        produto.setPreco(new BigDecimal("50.00"));
        produto.setCategoria(categoria);

        Produtos produtoSalvo = produtoRepositorio.save(produto);

        assertNotNull(
                produtoRepositorio.findById(produtoSalvo.getId()).orElse(null)
        );

        produtoRepositorio.deleteById(produtoSalvo.getId());

        assertEquals(false, produtoRepositorio.existsById(produtoSalvo.getId()));
    }
}
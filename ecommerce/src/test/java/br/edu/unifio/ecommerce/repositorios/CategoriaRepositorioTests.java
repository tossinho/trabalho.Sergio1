package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Categoria;

@SpringBootTest
public class CategoriaRepositorioTests {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    public void deveBuscarUmaCategoriaPorId() {
        Categoria categoria = categoriaRepositorio.findById((short) 1).orElseThrow();

        assertNotNull(categoria);
        assertEquals("informatica", categoria.getNome());
    }

    @Test
    public void deveInserirUmaCategoria(){
        Categoria categoria = new Categoria();
        
        categoria.setNome("Eletronicos");
        categoria.setDescriçao("Produtos eletronicos");

        Categoria categoriaSalva = categoriaRepositorio.save(categoria);

        assertNotNull(categoriaSalva);
        assertNotNull(categoriaSalva.getId());
        assertEquals("Eletronicos", categoriaSalva.getNome());
        assertEquals("Produtos eletronicos", categoriaSalva.getDescriçao());
    }
    @Test
public void deveListarCategorias() {
    int quantidadeAntes = categoriaRepositorio.findAll().size();

    Categoria categoria1 = new Categoria();
    categoria1.setNome("Eletronicos");
    categoria1.setDescriçao("Produtos eletronicos");

    Categoria categoria2 = new Categoria();
    categoria2.setNome("Roupas");
    categoria2.setDescriçao("Produtos de roupas");

    categoriaRepositorio.save(categoria1);
    categoriaRepositorio.save(categoria2);

    var categorias = categoriaRepositorio.findAll();

    assertNotNull(categorias);
    assertEquals(quantidadeAntes + 2, categorias.size());
    }
    @Test
public void deveAlterarUmaCategoria() {
    Categoria categoria = new Categoria();

    categoria.setNome("Eletronicos");
    categoria.setDescriçao("Produtos eletronicos");

    Categoria categoriaSalva = categoriaRepositorio.save(categoria);

    categoriaSalva.setNome("Eletronicos Atualizados");

    categoriaRepositorio.save(categoriaSalva);

    Categoria categoriaAlterada = categoriaRepositorio
            .findById(categoriaSalva.getId())
            .orElseThrow();

    assertEquals("Eletronicos Atualizados", categoriaAlterada.getNome());
    assertEquals("Produtos eletronicos", categoriaAlterada.getDescriçao());
    }
    @Test
public void deveExcluirUmaCategoria() {
    Categoria categoria = new Categoria();

    categoria.setNome("Categoria Para Excluir");
    categoria.setDescriçao("Categoria que será excluida");

    Categoria categoriaSalva = categoriaRepositorio.save(categoria);

    assertNotNull(categoriaRepositorio.findById(categoriaSalva.getId()).orElse(null));

    categoriaRepositorio.deleteById(categoriaSalva.getId());

    assertEquals(false, categoriaRepositorio.existsById(categoriaSalva.getId()));
    }
}

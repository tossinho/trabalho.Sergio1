package br.edu.unifio.ecommerce.repositorios;

import br.edu.unifio.ecommerce.entidades.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositorio extends JpaRepository<Produtos, Integer> {

}
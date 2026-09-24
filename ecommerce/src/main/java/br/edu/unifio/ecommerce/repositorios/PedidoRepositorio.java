package br.edu.unifio.ecommerce.repositorios;

import br.edu.unifio.ecommerce.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {

}
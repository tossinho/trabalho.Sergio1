package br.edu.unifio.ecommerce.repositorios;

import br.edu.unifio.ecommerce.entidades.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepositorio extends JpaRepository<Pagamento, Integer> {

}
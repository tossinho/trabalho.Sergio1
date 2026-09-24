package br.edu.unifio.ecommerce.repositorios;

import br.edu.unifio.ecommerce.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Short> {

}
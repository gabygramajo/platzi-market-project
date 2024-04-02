package com.gabydev.platzimarket.persistence.crud;

import com.gabydev.platzimarket.persistence.entity.Producto;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// como extiende de CrudRepository el cual es un componente de Spring de tipo @NoRepositoryBean, ProductoCrudRepository se convierte en un componente de Spring y se puede inyectar.
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    List<Producto> findByIdCategoria(int idCategoria);

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}

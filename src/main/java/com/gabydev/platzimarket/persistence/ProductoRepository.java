package com.gabydev.platzimarket.persistence;

import com.gabydev.platzimarket.domain.Product;
import com.gabydev.platzimarket.domain.repository.ProductRepository;
import com.gabydev.platzimarket.persistence.crud.ProductoCrudRepository;
import com.gabydev.platzimarket.persistence.entity.Producto;
import com.gabydev.platzimarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// ProductoRepository está enfocado al Dominio en vez de a una tabla puntual y asi desacoplarla de la BD.
@Repository
public class ProductoRepository implements ProductRepository {
    // DI -> Con @Autowired será Spring quien se encargará de crear las instancias, y no nosotros manualmente. Para que funcione correctamente la Clase a la que le aplicamos esta notación debe ser un Componente de Spring

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    // Para poder hacer la conversiones
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        // conversion de Productos a Products y con Optional
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        // Como no tengo un metodo mapper que me convierta un lista de Optionals utilizamos map para que nos retorne un Optional de lo que esté haciendo dentro de la expresión, en este caso pasar de producto a product
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {

        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        // como save espera un tipo Producto hay que hacer la conversión inversa
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}

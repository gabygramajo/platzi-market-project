package com.gabydev.platzimarket.persistence;

import com.gabydev.platzimarket.domain.Purchase;
import com.gabydev.platzimarket.domain.repository.PurchaseRepository;
import com.gabydev.platzimarket.persistence.crud.CompraCrudRepository;
import com.gabydev.platzimarket.persistence.entity.Compra;
import com.gabydev.platzimarket.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        //para poder guardarlo en la bd debemos hacer la conversion a Compra, ya que es la entidad con la que esta relacionada la tabla
        Compra compra = mapper.toCompra(purchase);
        // debemos de asegurarnos de que la informacion se guarde en cascada. Compra conoce los Productos y los Productos conocen a que Compra pertenecen con ComprasProducto
        compra.getProductos().forEach(producto -> producto.setCompra(compra));


        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}

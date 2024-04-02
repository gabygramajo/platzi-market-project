package com.gabydev.platzimarket.persistence.mapper;

import com.gabydev.platzimarket.domain.Category;
import com.gabydev.platzimarket.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    //Convertimos/mapeamos Categoria a Category
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    // para realizar un mapeo inverso automaticamente de Category a Categoria
    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true) // ya que no existe en Category
    Categoria toCategoria(Category category);
}

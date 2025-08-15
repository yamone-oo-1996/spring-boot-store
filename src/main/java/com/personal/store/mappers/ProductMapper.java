package com.personal.store.mappers;

import com.personal.store.dtos.ProductDto;
import com.personal.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    void update(ProductDto request, @MappingTarget Product product);
}

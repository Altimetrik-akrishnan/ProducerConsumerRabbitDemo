package com.onlineshop.products.catalogue.mapper;

import com.onlineshop.products.catalogue.dto.ProductDto;
import com.onlineshop.products.catalogue.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto mapToProductDto(Product product);

    Product mapToProduct(ProductDto productDto);

}

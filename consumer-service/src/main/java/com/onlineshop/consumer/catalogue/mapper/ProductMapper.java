package com.onlineshop.consumer.catalogue.mapper;

import com.onlineshop.consumer.catalogue.entity.Product;
import com.onlineshop.consumer.catalogue.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto mapToProductDto(Product product);

    Product mapToProduct(ProductDto productDto);

}

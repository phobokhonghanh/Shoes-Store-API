package fit.edu.tmdt.shoes_store_api.convert;

import fit.edu.tmdt.shoes_store_api.dto.ProductsDTO;
import fit.edu.tmdt.shoes_store_api.entities.Products;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Products toEntity(ProductsDTO productDTO) {
        return modelMapper.map(productDTO, Products.class);
    }

    public ProductsDTO toDTO(Products product) {
        return modelMapper.map(product, ProductsDTO.class);
    }
}

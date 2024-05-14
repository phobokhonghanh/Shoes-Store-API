package fit.edu.tmdt.shoes_store_api.convert;

import fit.edu.tmdt.shoes_store_api.dto.SizeProductsDTO;
import fit.edu.tmdt.shoes_store_api.entities.SizeProducts;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SizeProductsMapper {
    @Autowired
    private ModelMapper modelMapper;

    public SizeProducts toEntity(SizeProductsDTO sizeProductsDTO) {
        return modelMapper.map(sizeProductsDTO, SizeProducts.class);
    }

    public SizeProductsDTO toDTO(SizeProducts sizeProducts) {
        return modelMapper.map(sizeProducts, SizeProductsDTO.class);
    }
}

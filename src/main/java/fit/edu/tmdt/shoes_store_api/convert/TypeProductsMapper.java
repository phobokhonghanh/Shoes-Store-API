package fit.edu.tmdt.shoes_store_api.convert;

import fit.edu.tmdt.shoes_store_api.dto.TypeProductsDTO;
import fit.edu.tmdt.shoes_store_api.entities.TypeProducts;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeProductsMapper {
    @Autowired
    private ModelMapper modelMapper;

    public TypeProducts toEntity(TypeProductsDTO typeProductsDTO) {
        return modelMapper.map(typeProductsDTO, TypeProducts.class);
    }

    public TypeProductsDTO toDTO(TypeProducts typeProduct) {
        return modelMapper.map(typeProduct, TypeProductsDTO.class);
    }
}

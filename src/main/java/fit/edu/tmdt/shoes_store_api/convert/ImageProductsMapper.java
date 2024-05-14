package fit.edu.tmdt.shoes_store_api.convert;

import fit.edu.tmdt.shoes_store_api.dto.ImageProductsDTO;
import fit.edu.tmdt.shoes_store_api.entities.ImageProducts;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageProductsMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ImageProducts toEntity(ImageProductsDTO imageProductsDTO) {
        return modelMapper.map(imageProductsDTO, ImageProducts.class);
    }

    public ImageProductsDTO toDTO(ImageProducts imageProducts) {
        return modelMapper.map(imageProducts, ImageProductsDTO.class);
    }
}

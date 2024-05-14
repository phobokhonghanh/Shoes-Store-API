package fit.edu.tmdt.shoes_store_api.convert;

import fit.edu.tmdt.shoes_store_api.dto.BrandsDTO;
import fit.edu.tmdt.shoes_store_api.entities.Brands;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandsMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Brands toEntity(BrandsDTO brandsDTO) {
        Brands brands = modelMapper.map(brandsDTO, Brands.class);
        return brands;
    }

    public BrandsDTO toDTO(Brands brands) {
        BrandsDTO brandsDTO = modelMapper.map(brands, BrandsDTO.class);
        return brandsDTO;
    }
}

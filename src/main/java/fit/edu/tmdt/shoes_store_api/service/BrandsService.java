package fit.edu.tmdt.shoes_store_api.service;

import fit.edu.tmdt.shoes_store_api.dto.BrandsDTO;
import fit.edu.tmdt.shoes_store_api.entities.Brands;
import org.springframework.data.domain.Page;

public interface BrandsService {
    Page<BrandsDTO> getAll(Integer pageNo);

    BrandsDTO getBrand(Integer id);

    BrandsDTO createBrand(BrandsDTO brandsDTO);

    BrandsDTO updateBrand(BrandsDTO brandsDTO);

    void deleteBrand(String id);

}

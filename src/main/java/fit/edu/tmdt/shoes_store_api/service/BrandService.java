package fit.edu.tmdt.shoes_store_api.service;

import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandDTO;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandResponse;
import fit.edu.tmdt.shoes_store_api.entities.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService {
    Page<BrandResponse> getAll(Integer pageNo);
    List<BrandResponse> getAll();

    BrandResponse getBrand(Long id);
    Brand findById(Long id);

    BrandResponse createBrand(BrandDTO brandsDTO);

    BrandResponse updateBrand(BrandDTO brandsDTO);

    void deleteBrand(Long id);

}

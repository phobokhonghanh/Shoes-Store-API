package fit.edu.tmdt.shoes_store_api.service.impl;


import fit.edu.tmdt.shoes_store_api.convert.BrandsMapper;
import fit.edu.tmdt.shoes_store_api.dto.BrandsDTO;
import fit.edu.tmdt.shoes_store_api.entities.Brands;
import fit.edu.tmdt.shoes_store_api.repository.BrandsRepo;
import fit.edu.tmdt.shoes_store_api.service.BrandsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandsImpl implements BrandsService {
    @Autowired
    BrandsRepo brandsRepo;
    @Autowired
    BrandsMapper brandsMapper;

    @Override
    public Page<BrandsDTO> getAll(Integer pageNo) {
        return null;
    }

    @Override
    public BrandsDTO getBrand(Integer id) {
        Brands brands = brandsRepo.findById(id).get();
        return brandsMapper.toDTO(brands);
    }

    @Override
    public BrandsDTO createBrand(BrandsDTO brandsDTO) {
        Brands brands = brandsRepo.save(brandsMapper.toEntity(brandsDTO));
        return brandsMapper.toDTO(brands);
    }

    @Override
    public BrandsDTO updateBrand(BrandsDTO brandsDTO) {
        return null;
    }

    @Override
    public void deleteBrand(String id) {

    }
}

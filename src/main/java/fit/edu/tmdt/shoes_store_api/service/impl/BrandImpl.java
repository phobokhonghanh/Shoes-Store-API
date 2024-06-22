package fit.edu.tmdt.shoes_store_api.service.impl;


import fit.edu.tmdt.shoes_store_api.Utils.ImplUtil;
import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandDTO;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandResponse;
import fit.edu.tmdt.shoes_store_api.dto.Support.Status;
import fit.edu.tmdt.shoes_store_api.entities.Brand;
import fit.edu.tmdt.shoes_store_api.repository.BrandRepo;
import fit.edu.tmdt.shoes_store_api.repository.Specification.BrandSpecification;
import fit.edu.tmdt.shoes_store_api.service.BrandService;
import fit.edu.tmdt.shoes_store_api.service.SupportService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandImpl implements BrandService {
    @Autowired
    BrandRepo brandRepo;
    @Autowired
    SupportService supportService;
    @Autowired
    ImplUtil implUtil;
    @Autowired
    ConvertBase convertBase;

    @Override
    public Page<BrandResponse> getAll(Integer pageNo, Integer pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Specification<Brand> spec = Specification.where(BrandSpecification.containsTextInField(search, "name"));

        Page<Brand> brandEntity = brandRepo.findAll(spec, pageable);
        List<BrandResponse> brandDTO = convertBase.toListConvert(brandEntity.getContent(), BrandResponse.class);
        return new PageImpl<>(brandDTO, pageable, brandEntity.getTotalElements());
    }

    @Override
    public List<BrandResponse> getAllByActive() {
        return convertBase.toListConvert(brandRepo.findAllByStatusId(Status.UNLOCK), BrandResponse.class);
    }

    @Override
    public BrandResponse getBrand(Long id) {
        Optional<Brand> optional = brandRepo.findById(id);
        return implUtil.getOptional(optional, BrandResponse.class);
    }

    @Override
    public Brand findById(Long id) {
        Brand brand = brandRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Brand not found"));
        return brand;
    }

    @Override
    public BrandResponse createBrand(BrandDTO brandsDTO) {
        if ((brandRepo.existsByCode(brandsDTO.getCode()))) return null;
        Brand brand = brandRepo.save(convertBase.convert(brandsDTO, Brand.class));
        return convertBase.convert(brand, BrandResponse.class);
    }

    @Override
    public BrandResponse updateBrand(BrandDTO brandsDTO) {
        Brand brand = brandRepo.findByCode(brandsDTO.getCode());
        if (brand == null || !brand.getId().equals(brandsDTO.getId())) {
            return null;
        }

        if (brandsDTO.getName() != null) {
            brand.setName(brandsDTO.getName());
        }
        if (brandsDTO.getCode() != null) {
            brand.setCode(brandsDTO.getCode());
        }
        if (brandsDTO.getDescription() != null) {
            brand.setDescription(brandsDTO.getDescription());
        }
        if (brandsDTO.getStatus() != null) {
            brand.setStatus(supportService.getSupport(brandsDTO.getStatus().getId()));
        }

        Brand updatedBrand = brandRepo.save(brand);
        return convertBase.convert(updatedBrand, BrandResponse.class);
    }

    @Override
    public void deleteBrand(Long id) {

    }
}

package fit.edu.tmdt.shoes_store_api.service.impl;


import fit.edu.tmdt.shoes_store_api.Utils.ImplUtil;
import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandDTO;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandResponse;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeResponse;
import fit.edu.tmdt.shoes_store_api.entities.Brand;
import fit.edu.tmdt.shoes_store_api.repository.BrandRepo;
import fit.edu.tmdt.shoes_store_api.repository.SizeRepo;
import fit.edu.tmdt.shoes_store_api.service.BrandService;
import fit.edu.tmdt.shoes_store_api.service.SizeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SizeImpl implements SizeService {
    @Autowired
    SizeRepo sizeRepo;
    @Autowired
    ImplUtil implUtil;
    @Autowired
    ConvertBase convertBase;


    @Override
    public SizeResponse create(SizeDTO sizeDTO) {
        return null;
    }

    @Override
    public SizeResponse update(SizeDTO sizeDTO) {
        return null;
    }
}

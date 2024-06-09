package fit.edu.tmdt.shoes_store_api.service.impl;


import fit.edu.tmdt.shoes_store_api.Utils.ImplUtil;
import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeResponse;
import fit.edu.tmdt.shoes_store_api.repository.ImageRepo;
import fit.edu.tmdt.shoes_store_api.repository.SizeRepo;
import fit.edu.tmdt.shoes_store_api.service.ImageService;
import fit.edu.tmdt.shoes_store_api.service.SizeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageImpl implements ImageService {
    @Autowired
    ImageRepo imageRepo;
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

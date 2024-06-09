package fit.edu.tmdt.shoes_store_api.service.impl;


import fit.edu.tmdt.shoes_store_api.Utils.ImplUtil;
import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeResponse;
import fit.edu.tmdt.shoes_store_api.entities.Support;
import fit.edu.tmdt.shoes_store_api.repository.SizeRepo;
import fit.edu.tmdt.shoes_store_api.repository.SupportRepo;
import fit.edu.tmdt.shoes_store_api.service.SizeService;
import fit.edu.tmdt.shoes_store_api.service.SupportService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SupportImpl implements SupportService {
    @Autowired
    SupportRepo supportRepo;
    @Autowired
    ImplUtil implUtil;
    @Autowired
    ConvertBase convertBase;

    @Override
    public Support getSupport(String id) {
        Support support = supportRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Support status not found"));
        return support;
    }
}

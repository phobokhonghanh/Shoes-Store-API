package fit.edu.tmdt.shoes_store_api.service;

import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandDTO;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandResponse;
import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDTO;
import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDataType;
import fit.edu.tmdt.shoes_store_api.entities.Support;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SupportService {

    Support getSupport(String id);
    List<SupportDTO> getSupportData(SupportDataType type);


}

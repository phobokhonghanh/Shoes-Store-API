package fit.edu.tmdt.shoes_store_api.service;

import fit.edu.tmdt.shoes_store_api.dto.Size.SizeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeResponse;


public interface SizeService {

    SizeResponse create(SizeDTO sizeDTO);

    SizeResponse update(SizeDTO sizeDTO);

}

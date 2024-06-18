package fit.edu.tmdt.shoes_store_api.service;

import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandDTO;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandResponse;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductResponse;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeResponse;
import fit.edu.tmdt.shoes_store_api.entities.Support;
import fit.edu.tmdt.shoes_store_api.entities.Type;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TypeService {

    Type getType(Long id);

    List<TypeResponse> getAll();

    Page<TypeResponse> getAll(Integer pageNo, Integer pageSize, String search);

    TypeResponse createType(TypeDTO typeDTO);

    TypeResponse updateType(TypeDTO typeDTO);
}

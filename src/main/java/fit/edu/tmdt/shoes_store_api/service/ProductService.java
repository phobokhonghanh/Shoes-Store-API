package fit.edu.tmdt.shoes_store_api.service;

import fit.edu.tmdt.shoes_store_api.dto.Product.ProductDTO;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<ProductResponse>  getAll(Integer pageNo, Integer pageSize, String search, boolean sort, String filter, Integer brand, boolean active);
    ProductResponse getById(Long id);

    ProductResponse create(ProductDTO productDTO);

    ProductResponse update(ProductDTO productDTO);

    void delete(Long id);

}

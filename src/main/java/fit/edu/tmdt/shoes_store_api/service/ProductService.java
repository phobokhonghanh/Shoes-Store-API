package fit.edu.tmdt.shoes_store_api.service;

import fit.edu.tmdt.shoes_store_api.dto.Product.ProductDTO;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    Page<ProductResponse> getAll(Integer pageNo, Integer pageSize, String search, boolean sort, String filter, Integer brand, Integer type, String sex, boolean active);

    ProductResponse getById(Long id);

    ProductResponse create(ProductDTO productDTO, MultipartFile[] files);

    ProductResponse update(ProductDTO productDTO,MultipartFile[] files);

    void delete(Long id);

}

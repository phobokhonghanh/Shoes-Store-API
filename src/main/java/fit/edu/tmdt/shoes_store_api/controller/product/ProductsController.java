package fit.edu.tmdt.shoes_store_api.controller.product;


import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductDTO;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductResponse;
import fit.edu.tmdt.shoes_store_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("${api.prefix}")
public class ProductsController {
    @Autowired
    ProductService productService;

    @PostMapping("/admin/product")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductDTO productDTO) {
        ProductResponse saveProductDTO = productService.create(productDTO);
        return ResponseUtil.getResponse(saveProductDTO, CREATED);
    }

    @PatchMapping("/admin/product")
    public ResponseEntity<ProductResponse> update(@RequestBody ProductDTO productDTO) {
        ProductResponse saveProductDTO = productService.update(productDTO);
        return ResponseUtil.getResponse(saveProductDTO, CREATED);
    }
}

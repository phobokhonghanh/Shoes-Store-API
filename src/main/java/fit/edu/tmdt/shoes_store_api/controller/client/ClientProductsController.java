package fit.edu.tmdt.shoes_store_api.controller.client;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductResponse;
import fit.edu.tmdt.shoes_store_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${api.prefix}/client-api/product")
public class ClientProductsController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/{productId}", produces = "application/json")
    public ResponseEntity<ProductResponse> getProductByID(@PathVariable Long productId) {
        ProductResponse productsDTO = productService.getById(productId);
        return ResponseUtil.getResponse(productsDTO, OK);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<Page<ProductResponse>> getAll(@RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                                          @RequestParam(name = "size", required = false, defaultValue = "10") Integer pageSize,
                                                          @RequestParam(name = "search", required = false, defaultValue = "") String search,
                                                          @RequestParam(name = "sort", required = false, defaultValue = "true") boolean sort,
                                                          @RequestParam(name = "filter", required = false, defaultValue = "name") String filter,
                                                          @RequestParam(name = "brand", required = false) Integer brand,
                                                          @RequestParam(name = "active", required = false, defaultValue = "true") boolean active) {
        Page<ProductResponse> list = productService.getAll(pageNo, pageSize, search, sort, filter,brand, active);
        return ResponseUtil.getResponse(list, OK);
    }
}

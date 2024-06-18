package fit.edu.tmdt.shoes_store_api.controller.brand;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandDTO;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandResponse;
import fit.edu.tmdt.shoes_store_api.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("${api.prefix}/admin/brand")
public class BrandsController {
    @Autowired
    BrandService brandService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<Page<BrandResponse>> getAll(@RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                                      @RequestParam(name = "size", required = false, defaultValue = "10") Integer pageSize,
                                                      @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        return ResponseUtil.getResponse(brandService.getAll(pageNo, pageSize, search), OK);
    }

    @PostMapping("")
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandDTO brandDTO) {
        return ResponseUtil.getResponse(brandService.createBrand(brandDTO), CREATED);
    }

    @PatchMapping("")
    public ResponseEntity<BrandResponse> updateBrand(@RequestBody BrandDTO brandDTO) {
        return ResponseUtil.getResponse(brandService.updateBrand(brandDTO), CREATED);
    }
}

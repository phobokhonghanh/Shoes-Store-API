package fit.edu.tmdt.shoes_store_api.controller.brand;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandDTO;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandResponse;
import fit.edu.tmdt.shoes_store_api.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("${api.prefix}/admin")
public class BrandsController {
    @Autowired
    BrandService brandService;

    @PostMapping("/brand")
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandDTO brandDTO) {
        BrandResponse saveBrandDTO = brandService.createBrand(brandDTO);
        return ResponseUtil.getResponse(saveBrandDTO, CREATED);
    }
    @PatchMapping("/brand")
    public ResponseEntity<BrandResponse> updateBrand(@RequestBody BrandDTO brandDTO) {
        BrandResponse saveBrandDTO = brandService.updateBrand(brandDTO);
        return ResponseUtil.getResponse(saveBrandDTO, CREATED);
    }
}

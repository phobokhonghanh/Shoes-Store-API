package fit.edu.tmdt.shoes_store_api.controller.client;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandResponse;
import fit.edu.tmdt.shoes_store_api.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${api.prefix}/client-api/brand")
public class ClientBrandsController {
    @Autowired
    BrandService brandService;

    @GetMapping(value = "/{brandId}", produces = "application/json")
    public ResponseEntity<BrandResponse> getBrandByID(@PathVariable Long brandId) {
        BrandResponse brandsDTO = brandService.getBrand(brandId);
        return ResponseUtil.getResponse(brandsDTO, OK);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<BrandResponse>> getAll() {
        List<BrandResponse> list = brandService.getAll();
        return ResponseUtil.getResponse(list, OK);
    }
}

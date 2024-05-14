package fit.edu.tmdt.shoes_store_api.controller;

import fit.edu.tmdt.shoes_store_api.dto.BrandsDTO;
import fit.edu.tmdt.shoes_store_api.service.BrandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${api.prefix}")
public class BrandsController {
    @Autowired
    BrandsService brandsService;

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleDataAccessException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/admin/brands")
    public ResponseEntity<BrandsDTO> createBrand(@RequestBody BrandsDTO brandsDTO) {
        BrandsDTO saveBrandDTO = brandsService.createBrand(brandsDTO);
        if (saveBrandDTO != null) {
            return new ResponseEntity<>(saveBrandDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/user/brands/{brandId}", produces = "application/json")
    public ResponseEntity<BrandsDTO> getBrandByID(@PathVariable Integer brandId) {
        return new ResponseEntity<>(brandsService.getBrand(brandId), OK);
    }
}

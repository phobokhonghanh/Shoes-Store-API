package fit.edu.tmdt.shoes_store_api.controller.type;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandDTO;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandResponse;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeResponse;
import fit.edu.tmdt.shoes_store_api.service.BrandService;
import fit.edu.tmdt.shoes_store_api.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("${api.prefix}/admin/type")
public class TypesController {
    @Autowired
    TypeService typeService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<Page<TypeResponse>> getAll(@RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer pageSize,
                                                     @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        return ResponseUtil.getResponse(typeService.getAll(pageNo, pageSize, search), OK);
    }

    @PostMapping("")
    public ResponseEntity<TypeResponse> create(@RequestBody TypeDTO typeDTO) {
        return ResponseUtil.getResponse(typeService.createType(typeDTO), CREATED);
    }

    @PatchMapping("")
    public ResponseEntity<TypeResponse> update(@RequestBody TypeDTO typeDTO) {
        return ResponseUtil.getResponse(typeService.updateType(typeDTO), CREATED);
    }
}

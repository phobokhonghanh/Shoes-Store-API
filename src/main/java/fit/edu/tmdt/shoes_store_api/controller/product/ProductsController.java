package fit.edu.tmdt.shoes_store_api.controller.product;


import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.constant.Message;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductDTO;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductResponse;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeDTO;
import fit.edu.tmdt.shoes_store_api.entities.Type;
import fit.edu.tmdt.shoes_store_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("${api.prefix}/admin/product")
public class ProductsController {
    @Autowired
    ProductService productService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity create(@RequestPart ProductDTO productDTO, @RequestParam MultipartFile[] files) {
        ProductResponse saveProductDTO = productService.create(productDTO, files);
        return ResponseUtil.getResponseWithMessage(saveProductDTO, Message.CODE_EXIST, CREATED);
    }

    @PatchMapping
    public ResponseEntity update(@RequestPart ProductDTO productDTO, @RequestParam(required = false) MultipartFile[] files) {
        ProductResponse saveProductDTO = productService.update(productDTO, files);
        return ResponseUtil.getResponseWithMessage(saveProductDTO, Message.CODE_EXIST, CREATED);
    }


}

package fit.edu.tmdt.shoes_store_api.controller.client;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeResponse;
import fit.edu.tmdt.shoes_store_api.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${api.prefix}/client-api/type")
public class ClientTypesController {
    @Autowired
    TypeService typeService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<TypeResponse>> getAll() {
        return ResponseUtil.getResponse(typeService.getAll(), OK);
    }

}

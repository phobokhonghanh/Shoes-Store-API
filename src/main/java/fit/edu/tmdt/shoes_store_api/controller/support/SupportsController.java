package fit.edu.tmdt.shoes_store_api.controller.support;

import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDTO;
import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDataType;
import fit.edu.tmdt.shoes_store_api.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}")
public class SupportsController {
    @Autowired
    SupportService supportService;

    @GetMapping(value = "/admin/support-data", produces = "application/json")
    public ResponseEntity<List<SupportDTO>> getSupportData(@RequestParam String type) {
        try {
            SupportDataType dataType = SupportDataType.valueOf(type.toUpperCase());
            List<SupportDTO> data = supportService.getSupportData(dataType);
            return ResponseEntity.ok(data);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

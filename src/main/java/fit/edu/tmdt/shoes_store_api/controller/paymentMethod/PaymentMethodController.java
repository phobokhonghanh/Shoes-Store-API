package fit.edu.tmdt.shoes_store_api.controller.paymentMethod;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.dto.Payment.PaymentMethodDTO;
import fit.edu.tmdt.shoes_store_api.dto.Payment.PaymentMethodResponse;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeResponse;
import fit.edu.tmdt.shoes_store_api.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${api.prefix}/admin/payment-method")
public class PaymentMethodController {
    @Autowired
    PaymentMethodService paymentMethodService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<Page<PaymentMethodResponse>> getPaymentMethod(@RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                                                        @RequestParam(name = "size", required = false, defaultValue = "10") Integer pageSize,
                                                                        @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        return ResponseUtil.getResponse(paymentMethodService.getAll(pageNo, pageSize, search), OK);
    }

    @PostMapping("")
    public ResponseEntity<PaymentMethodResponse> create(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        return ResponseUtil.getResponse(paymentMethodService.create(paymentMethodDTO), CREATED);
    }

    @PatchMapping("")
    public ResponseEntity<PaymentMethodResponse> update(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        return ResponseUtil.getResponse(paymentMethodService.update(paymentMethodDTO), CREATED);
    }
}

package fit.edu.tmdt.shoes_store_api.controller.client;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.dto.Payment.PaymentMethodResponse;
import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDTO;
import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDataType;
import fit.edu.tmdt.shoes_store_api.service.PaymentMethodService;
import fit.edu.tmdt.shoes_store_api.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("${api.prefix}/client-api/payment-method")
public class ClientPaymentMethodController {
    @Autowired
    PaymentMethodService paymentMethodService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<PaymentMethodResponse>> getPaymentMethod() {
        return ResponseUtil.getResponse(paymentMethodService.getAll(), OK);
    }
}

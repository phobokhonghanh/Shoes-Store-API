package fit.edu.tmdt.shoes_store_api.dto.Payment.paypal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaypalCheckoutResponse {
    private String paypalUrl;
}
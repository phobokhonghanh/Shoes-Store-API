package fit.edu.tmdt.shoes_store_api.dto.Payment;

import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodResponse {
    private Long id;
    private String name;
    private String code;
    private SupportDTO supportStatus;
}

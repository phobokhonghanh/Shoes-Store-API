package fit.edu.tmdt.shoes_store_api.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentResponse {
    private List<NotifyOrderResponse> list = new ArrayList<>();
    private String linkPaypal;
}

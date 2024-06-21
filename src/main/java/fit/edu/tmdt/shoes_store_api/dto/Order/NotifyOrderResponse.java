package fit.edu.tmdt.shoes_store_api.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotifyOrderResponse {
    private Long idSize;
    private String nameProduct;
    private Long quantityWarehouse ;
}

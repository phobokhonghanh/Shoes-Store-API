package fit.edu.tmdt.shoes_store_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDTO {
    private Integer idOrderDetail;
    private OrdersDTO idOrder;
    private ProductsDTO idProduct;
    private String nameSize;
    private Integer quantity;
    private String price;
}

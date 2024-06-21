package fit.edu.tmdt.shoes_store_api.dto.OrderDetail;

import fit.edu.tmdt.shoes_store_api.dto.Size.SizeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private SizeDTO size;
    private Long quantity;
    private BigDecimal price;
    private Long order;
}
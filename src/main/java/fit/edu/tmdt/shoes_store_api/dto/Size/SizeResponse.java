package fit.edu.tmdt.shoes_store_api.dto.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeResponse {
    private Long id;
    private String name;
    private String quantity;
    private String price;
    private String salePercent;
    private String description;
}

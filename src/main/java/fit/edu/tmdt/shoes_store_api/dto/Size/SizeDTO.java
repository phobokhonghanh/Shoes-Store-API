package fit.edu.tmdt.shoes_store_api.dto.Size;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeDTO {
    private Long id;
    private Long product;
    private String name;
    private Long quantity;
    private String price;
    private String salePercent;
    private String description;

    public SizeDTO(Long id) {
        this.id = id;
    }
}

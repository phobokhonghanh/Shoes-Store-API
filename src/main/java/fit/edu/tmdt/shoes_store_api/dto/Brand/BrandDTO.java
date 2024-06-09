package fit.edu.tmdt.shoes_store_api.dto.Brand;

import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    private Long id;
    private String name;
    private String code;
    private String description;
    private SupportDTO status;

    public BrandDTO(Long id) {
        this.id = id;
    }
}

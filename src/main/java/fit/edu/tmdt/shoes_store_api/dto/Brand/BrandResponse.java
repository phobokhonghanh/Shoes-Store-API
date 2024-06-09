package fit.edu.tmdt.shoes_store_api.dto.Brand;

import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponse {
    private Long id;
    private Instant createdAt;
    private String name;
    private String code;
    private String description;
    private SupportDTO status;
}

package fit.edu.tmdt.shoes_store_api.dto.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeDTO {
    private Long id;
    private String name;
    public TypeDTO(Long id) {
        this.id = id;
    }
}

package fit.edu.tmdt.shoes_store_api.dto.Support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupportDTO {
    private String id;
    private String value;
    private String infor;
    private String description;

    public SupportDTO(String id) {
        this.id = id;
    }
}

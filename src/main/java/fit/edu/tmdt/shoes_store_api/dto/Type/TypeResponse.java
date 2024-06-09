package fit.edu.tmdt.shoes_store_api.dto.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeResponse {
    private Long id;
    private Instant createdAt;
    private String name;
}

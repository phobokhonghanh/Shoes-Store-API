package fit.edu.tmdt.shoes_store_api.dto.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageDTO {
    private Long id;
    private String path;
    private boolean isThumbnail;
}

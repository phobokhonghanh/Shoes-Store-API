package fit.edu.tmdt.shoes_store_api.dto.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {
    private Long id;
    private String path;
    private boolean isThumbnail;
}

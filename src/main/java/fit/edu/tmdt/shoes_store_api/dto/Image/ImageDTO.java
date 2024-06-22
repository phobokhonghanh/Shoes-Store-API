package fit.edu.tmdt.shoes_store_api.dto.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageDTO {
    private Long id;
    private boolean isThumbnail;
}

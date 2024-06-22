package fit.edu.tmdt.shoes_store_api.service;

import fit.edu.tmdt.shoes_store_api.dto.Size.SizeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeResponse;
import fit.edu.tmdt.shoes_store_api.entities.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String load(MultipartFile file);

    void deleteSource(String path);
    Image getSource(String path);
}

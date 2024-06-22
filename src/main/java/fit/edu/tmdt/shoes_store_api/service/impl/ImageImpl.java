package fit.edu.tmdt.shoes_store_api.service.impl;


import fit.edu.tmdt.shoes_store_api.Utils.ImplUtil;
import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeResponse;
import fit.edu.tmdt.shoes_store_api.entities.Image;
import fit.edu.tmdt.shoes_store_api.repository.ImageRepo;
import fit.edu.tmdt.shoes_store_api.repository.SizeRepo;
import fit.edu.tmdt.shoes_store_api.service.ImageService;
import fit.edu.tmdt.shoes_store_api.service.SizeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

@Log4j2
@Service
@AllArgsConstructor
public class ImageImpl implements ImageService {
    @Autowired
    ImageRepo imageRepo;
    @Autowired
    ImplUtil implUtil;
    @Autowired
    ConvertBase convertBase;

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    private static final Path staticPath = Paths.get("src/main/resources/static");
    private static final Path imagePath = Paths.get("admin/img/upload/product");

    @Override
    public String load(MultipartFile input) {
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            try {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String uid = implUtil.generateId();
        Path file = CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(uid + input.getOriginalFilename());
        if (!Files.exists(file)) {
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(input.getBytes());
            } catch (IOException e) {
                log.warn("Can't create file image");
                e.printStackTrace();
            }
        }
        StringTokenizer stringTokenizer = new StringTokenizer(imagePath.resolve(uid + input.getOriginalFilename()).toString(), "\\");
        String s = "";
        while (stringTokenizer.hasMoreTokens()) {
            s += stringTokenizer.nextToken() + "/";
        }
        return s.substring(0, s.length() - 1);
    }

    @Override
    public void deleteSource(String path) {
        if (path != null) {
            try {
                Path filePath = CURRENT_FOLDER.resolve(staticPath).resolve(path);
                if (Files.exists(filePath)) {
                    Files.delete(filePath);
                    System.out.println("File deleted successfully: " + filePath);
                } else {
                    System.err.println("File does not exist: " + filePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Image or image path is null");
        }
    }

    @Override
    public Image getSource(String path) {
        return null;
    }
}

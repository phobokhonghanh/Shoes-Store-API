package fit.edu.tmdt.shoes_store_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageProductsDTO {
    private Integer idImage;
    private String path;
    private ProductsDTO idProduct;
    private LocalDateTime createAt;

   }

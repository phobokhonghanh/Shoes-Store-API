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
public class ProductsDTO {
    private Integer idProduct;
    private String nameProduct;
    private Integer starReview;
    private String supportStatus;
    private String price;
    private String discountPercent;
    private BrandsDTO idBrand;
    private TypeProductsDTO idTypeProduct;
    private SupportDTO supportSex;
    private LocalDateTime createAt;
}

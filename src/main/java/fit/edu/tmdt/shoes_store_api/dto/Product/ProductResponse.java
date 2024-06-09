package fit.edu.tmdt.shoes_store_api.dto.Product;

import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandResponse;
import fit.edu.tmdt.shoes_store_api.dto.Image.ImageResponse;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeResponse;
import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDTO;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String code;
    private String shortDescription;
    private String description;
    private SupportDTO status;
    private SupportDTO sex;
    private TypeResponse type;
    private BrandResponse brand;
    private List<SizeResponse> sizes;
    private List<ImageResponse> images;

}

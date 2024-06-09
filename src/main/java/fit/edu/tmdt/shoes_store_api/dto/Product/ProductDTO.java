package fit.edu.tmdt.shoes_store_api.dto.Product;

import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandDTO;
import fit.edu.tmdt.shoes_store_api.dto.Image.ImageDTO;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDTO;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String code;
    private String shortDescription;
    private String description;
    private SupportDTO status;
    private SupportDTO sex;
    private TypeDTO type;
    private BrandDTO brand;
    private List<SizeDTO> sizes;
    private List<ImageDTO> images;
}

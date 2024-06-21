package fit.edu.tmdt.shoes_store_api.dto.OrderDetail;

import fit.edu.tmdt.shoes_store_api.dto.Image.ImageResponse;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductResponse;
import fit.edu.tmdt.shoes_store_api.dto.Size.SizeResponse;
import fit.edu.tmdt.shoes_store_api.entities.Product;
import fit.edu.tmdt.shoes_store_api.entities.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private Long id;
    private ProductOrderDetailResponse product;
    private String size;
    private Long quantity;
    private String price;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductOrderDetailResponse {
        private Long id;
        private String name;
        private List<ImageResponse> images;
    }
}


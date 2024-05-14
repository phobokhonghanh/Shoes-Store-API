package fit.edu.tmdt.shoes_store_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image_products")
public class ImageProducts {
    @Id
    @Column(name = "id_image")
    private Integer idImage;

    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Products idProduct;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}

package fit.edu.tmdt.shoes_store_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Products {
    @Id
    @Column(name = "id_product")
    private Integer idProduct;

    @Column(name = "name_product")
    private String nameProduct;

    @Column(name = "star_review")
    private Integer starReview;

    @ManyToOne
    @JoinColumn(name = "support_status")
    private Support supportStatus;

    @Column(name = "price")
    private String price;

    @Column(name = "discount_percent")
    private String discountPercent;
    @ManyToOne
    @JoinColumn(name = "id_brand")
    private Brands idBrand;
    @ManyToOne

    @JoinColumn(name = "id_type_product")
    private TypeProducts idTypeProduct;
    @ManyToOne
    @JoinColumn(name = "support_sex")
    private Support supportSex;

    @Column(name = "create_at")
    private LocalDateTime createAt;
}

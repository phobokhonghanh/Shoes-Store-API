package fit.edu.tmdt.shoes_store_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "size_products")
public class SizeProducts {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Products idProduct;

    @Id
    @Column(name = "name_size")
    private String nameSize;

    @Column(name = "quantity_available")
    private Integer quantityAvailable;
}

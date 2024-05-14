package fit.edu.tmdt.shoes_store_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @Column(name = "id_order_detail")
    private Integer idOrderDetail;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Orders idOrder;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Products idProduct;

    @Column(name = "name_size")
    private String nameSize;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private String price;
}
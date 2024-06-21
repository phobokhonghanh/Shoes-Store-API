package fit.edu.tmdt.shoes_store_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "payment_method")
public class PaymentMethod  extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "code", nullable = false, unique = true, length = 255)
    private String code;

    @ManyToOne
    @JoinColumn(name = "support_status", referencedColumnName = "id", nullable = false)
    private Support status;
}

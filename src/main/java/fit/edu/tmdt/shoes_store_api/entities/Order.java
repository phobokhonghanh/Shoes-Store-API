package fit.edu.tmdt.shoes_store_api.entities;
import fit.edu.tmdt.shoes_store_api.constant.PaymentMethodType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "`order`") // `order` là từ khóa trong SQL, nên cần wrap trong dấu `` để tránh xung đột
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "code", nullable = false, unique = true, length = 255)
    private String code;

    @Column(name = "to_name", length = 255)
    private String toName;

    @Column(name = "to_phone", length = 255)
    private String toPhone;

    @Column(name = "to_address", length = 255)
    private String toAddress;

    @Column(name = "to_ward_name", length = 255)
    private String toWardName;

    @Column(name = "to_district_name", length = 255)
    private String toDistrictName;

    @Column(name = "to_province_name", length = 255)
    private String toProvinceName;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "size_id", nullable = false)
    private Long sizeId;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "price", nullable = false, length = 255)
    private String price;

    @Column(name = "total_amount", nullable = false, precision = 15, scale = 5)
    private BigDecimal totalAmount;

    @Column(name = "tax", nullable = false, precision = 15, scale = 5)
    private BigDecimal tax;

    @Column(name = "shipping_cost", nullable = false, precision = 15, scale = 5)
    private BigDecimal shippingCost;

    @Column(name = "total_pay", nullable = false, precision = 15, scale = 5)
    private BigDecimal totalPay;

    @Column(name = "payment_method_type", length = 255)
    @Enumerated(EnumType.STRING)
    private PaymentMethodType paymentMethodType;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Account account;

    @Column(name = "payment_method_order_id", length = 255)
    private String paymentMethodOrderId;

    @ManyToOne
    @JoinColumn(name = "support_status", referencedColumnName = "id", nullable = false)
    private Support status;
}

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
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "id_order")
    private Integer idOrder;

    @Column(name = "email_customer")
    private String emailCustomer;

    @Column(name = "to_name")
    private String toName;

    @Column(name = "to_phone")
    private String toPhone;

    @Column(name = "to_address")
    private String toAddress;

    @Column(name = "to_ward_name")
    private String toWardName;

    @Column(name = "to_district_name")
    private String toDistrictName;

    @Column(name = "to_province_name")
    private String toProvinceName;

    @Column(name = "to_ward_id")
    private String toWardId;

    @Column(name = "to_district_id")
    private String toDistrictId;

    @Column(name = "to_province_id")
    private String toProvinceId;

    @Column(name = "note")
    private String note;

    @Column(name = "ship_price")
    private String shipPrice;

    @Column(name = "order_value")
    private String orderValue;

    @Column(name = "total_price")
    private String totalPrice;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;
    @ManyToOne
    @JoinColumn(name = "support_status")
    private Support supportStatus;
}

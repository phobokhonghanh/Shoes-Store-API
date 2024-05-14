package fit.edu.tmdt.shoes_store_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {
    private Integer idOrder;
    private String emailCustomer;
    private String toName;
    private String toPhone;
    private String toAddress;
    private String toWardName;
    private String toDistrictName;
    private String toProvinceName;
    private String toWardId;
    private String toDistrictId;
    private String toProvinceId;
    private String note;
    private String shipPrice;
    private String orderValue;
    private String totalPrice;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private SupportDTO supportStatus;
}

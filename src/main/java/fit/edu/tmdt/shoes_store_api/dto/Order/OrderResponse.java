package fit.edu.tmdt.shoes_store_api.dto.Order;

import fit.edu.tmdt.shoes_store_api.dto.Size.SizeResponse;
import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDTO;
import fit.edu.tmdt.shoes_store_api.dto.User.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private Instant createdAt;
    private String code;
    private String toName;
    private String toPhone;
    private String toAddress;
    private String toWardName;
    private String toDistrictName;
    private String toProvinceName;
    private String note;
    private SizeResponse size;
    private Long quantity;
    private String price;
    private BigDecimal totalAmount;
    private BigDecimal tax;
    private BigDecimal shippingCost;
    private BigDecimal totalPay;
    private String paymentMethodType;
    private UserResponse user;
    private String paymentMethodOrderId;
    private SupportDTO status;
}

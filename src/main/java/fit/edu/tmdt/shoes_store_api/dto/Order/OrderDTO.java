package fit.edu.tmdt.shoes_store_api.dto.Order;

import fit.edu.tmdt.shoes_store_api.dto.OrderDetail.OrderDetailDTO;
import fit.edu.tmdt.shoes_store_api.dto.User.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String toName;
    private String toPhone;
    private String toAddress;
    private String toWardName;
    private String toDistrictName;
    private String toProvinceName;
    private String note;
    private List<OrderDetailDTO> listOrderDetail;
    private BigDecimal totalAmount;
    private BigDecimal tax;
    private BigDecimal shippingCost;
    private BigDecimal totalPay;
    private String paymentMethodType;
    private AccountDTO account;

    public OrderDTO(String toName) {
        this.toName = toName;
    }
}
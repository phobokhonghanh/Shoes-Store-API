package fit.edu.tmdt.shoes_store_api.convert;

import fit.edu.tmdt.shoes_store_api.dto.OrderDetail.OrderDetailResponse;
import fit.edu.tmdt.shoes_store_api.dto.Product.ProductResponse;
import fit.edu.tmdt.shoes_store_api.entities.OrderDetail;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ConvertOrderDetail {
    @Autowired
    ConvertBase convertBase;

    public OrderDetailResponse convert(OrderDetail orderDetail) {
        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        orderDetailResponse.setId(orderDetail.getId());
        orderDetailResponse.setProduct(convertBase.convert(orderDetail.getSize().getProduct(), OrderDetailResponse.ProductOrderDetailResponse.class));
        orderDetailResponse.setSize(orderDetail.getSize().getName());
        orderDetailResponse.setQuantity(orderDetail.getQuantity());
        orderDetailResponse.setPrice(orderDetail.getPrice());
        return orderDetailResponse;
    }

    public List<OrderDetailResponse> toListConvert(List<OrderDetail> list) {
        List<OrderDetailResponse> result = new ArrayList<>();
        for (OrderDetail e : list) {
            result.add(convert(e));
        }
        return result;
    }
}

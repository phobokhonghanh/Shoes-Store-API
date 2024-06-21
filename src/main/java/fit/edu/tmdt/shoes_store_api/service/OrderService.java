package fit.edu.tmdt.shoes_store_api.service;

import fit.edu.tmdt.shoes_store_api.dto.Order.NotifyOrderResponse;
import fit.edu.tmdt.shoes_store_api.dto.Order.OrderDTO;
import fit.edu.tmdt.shoes_store_api.dto.Order.OrderPaymentResponse;
import fit.edu.tmdt.shoes_store_api.dto.Order.OrderResponse;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeDTO;
import fit.edu.tmdt.shoes_store_api.dto.Type.TypeResponse;
import fit.edu.tmdt.shoes_store_api.entities.Order;
import fit.edu.tmdt.shoes_store_api.entities.Type;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    Page<OrderResponse> getAllByAccountId(Long accountId,Integer pageNo, Integer pageSize, String search);

    Page<OrderResponse> getAll(Integer pageNo, Integer pageSize, String search);

    OrderPaymentResponse create(OrderDTO orderDTO);

    OrderResponse updateStatus(String code, String status);
}

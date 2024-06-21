package fit.edu.tmdt.shoes_store_api.service.impl;


import fit.edu.tmdt.shoes_store_api.Utils.ImplUtil;
import fit.edu.tmdt.shoes_store_api.constant.PaymentMethodType;
import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import fit.edu.tmdt.shoes_store_api.convert.ConvertOrderDetail;
import fit.edu.tmdt.shoes_store_api.dto.Order.NotifyOrderResponse;
import fit.edu.tmdt.shoes_store_api.dto.Order.OrderDTO;
import fit.edu.tmdt.shoes_store_api.dto.Order.OrderPaymentResponse;
import fit.edu.tmdt.shoes_store_api.dto.Order.OrderResponse;
import fit.edu.tmdt.shoes_store_api.dto.OrderDetail.OrderDetailDTO;
import fit.edu.tmdt.shoes_store_api.dto.Support.Status;
import fit.edu.tmdt.shoes_store_api.entities.Order;
import fit.edu.tmdt.shoes_store_api.entities.OrderDetail;
import fit.edu.tmdt.shoes_store_api.entities.Size;
import fit.edu.tmdt.shoes_store_api.entities.Support;
import fit.edu.tmdt.shoes_store_api.repository.OrderDetailRepo;
import fit.edu.tmdt.shoes_store_api.repository.OrderRepo;
import fit.edu.tmdt.shoes_store_api.repository.SizeRepo;
import fit.edu.tmdt.shoes_store_api.repository.Specification.OrderSpecification;
import fit.edu.tmdt.shoes_store_api.service.OrderService;
import fit.edu.tmdt.shoes_store_api.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class OrderImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    SizeRepo sizeRepo;
    @Autowired
    OrderDetailRepo orderDetailRepo;
    @Autowired
    ImplUtil implUtil;
    @Autowired
    ConvertBase convertBase;
    @Autowired
    ConvertOrderDetail convertOrderDetail;
    @Autowired
    PaymentService paymentService;


    @Override
    public Page<OrderResponse> getAllByAccountId(Long accountId, Integer pageNo, Integer pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Specification<Order> accountSpec = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("account").get("id"), accountId);

        // Create a specification with the search criteria
        Specification<Order> searchSpec = OrderSpecification.containsTextInField(search, "code");

        // Combine the specifications
        Specification<Order> combinedSpec = accountSpec.and(searchSpec);

        // Fetch the orders from the repository
        Page<Order> orders = orderRepo.findAll(combinedSpec, pageable);

        // Convert the orders to OrderResponse
        List<OrderResponse> listOrderResponse = orders.getContent().stream()
                .map(order -> {
                    OrderResponse orderResponse = convertBase.convert(order, OrderResponse.class);
                    orderResponse.setListOrderDetail(convertOrderDetail.toListConvert(order.getOrderDetails()));
                    return orderResponse;
                })
                .collect(Collectors.toList());
        return new PageImpl<>(listOrderResponse, pageable, orders.getTotalElements());
    }

    @Override
    public Page<OrderResponse> getAll(Integer pageNo, Integer pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Specification<Order> spec = Specification.where(OrderSpecification.containsTextInField(search, "code"));

        Page<Order> pageOrder = orderRepo.findAll(spec, pageable);
        List<OrderResponse> listOrderResponse = pageOrder.getContent().stream()
                .map(order -> {
                    OrderResponse orderResponse = convertBase.convert(order, OrderResponse.class);
                    orderResponse.setListOrderDetail(convertOrderDetail.toListConvert(order.getOrderDetails()));
                    return orderResponse;
                })
                .collect(Collectors.toList());
        return new PageImpl<>(listOrderResponse, pageable, pageOrder.getTotalElements());
    }

    BigDecimal calculateTotal(BigDecimal price, BigDecimal quantity) {
        return (price.multiply(quantity));
    }

    @Override
    public OrderPaymentResponse create(OrderDTO orderDTO) {
        Order order = convertBase.convert(orderDTO, Order.class);
        order.setCode(implUtil.generateId());
        order.setStatus(new Support(Status.ORDER_PROCESSING));
        Order saveOrder = orderRepo.save(order);
        List<NotifyOrderResponse> notifyFailed = new ArrayList<>();
        BigDecimal subTotal = BigDecimal.valueOf(0);
        for (OrderDetailDTO od : orderDTO.getListOrderDetail()) {
            Size size = sizeRepo.findById(od.getSize().getId()).orElse(null);
            if (size != null) {
                if (size.getQuantity() == 0) {
                    subTotal = subTotal.add(calculateTotal(od.getPrice(), (BigDecimal.valueOf(od.getQuantity()))));
                    notifyFailed.add(new NotifyOrderResponse(size.getId(), size.getProduct().getName(), size.getQuantity()));
                } else {
                    if (od.getQuantity() < size.getQuantity()) {
                        OrderDetail orderDetail = convertBase.convert(od, OrderDetail.class);
                        orderDetail.setOrder(saveOrder);
                        orderDetailRepo.save(orderDetail);
                        size.setQuantity(size.getQuantity() - od.getQuantity());
                        sizeRepo.save(size);
                    } else {
                        subTotal = subTotal.add(calculateTotal(od.getPrice(), (BigDecimal.valueOf(od.getQuantity()))));
                        notifyFailed.add(new NotifyOrderResponse(size.getId(), size.getProduct().getName(), size.getQuantity()));
                    }
                }
            } else {
                subTotal = subTotal.add(calculateTotal(od.getPrice(), (BigDecimal.valueOf(od.getQuantity()))));
            }
        }
        saveOrder.setTotalAmount(saveOrder.getTotalAmount().subtract(subTotal));
        saveOrder.setTotalPay(saveOrder.getTotalPay().subtract(subTotal));
        String link = null;
        if (saveOrder.getPaymentMethodType() == PaymentMethodType.CASH) {
            orderRepo.save(saveOrder);
        }
        if (saveOrder.getPaymentMethodType() == PaymentMethodType.PAYPAL) {
            link = paymentService.paymentByPaypal(saveOrder);
        }
        OrderPaymentResponse orderPaymentResponse = new OrderPaymentResponse(notifyFailed, link);
        return orderPaymentResponse;
    }


    @Override
    public OrderResponse updateStatus(String code, String status) {
        Order order = orderRepo.findByCode(code);
        if (order == null || status == null) return null;
        order.setStatus(new Support(status));
        Order orderSave = orderRepo.save(order);
        return convertBase.convert(orderSave, OrderResponse.class);
    }
}

package fit.edu.tmdt.shoes_store_api.service;

import fit.edu.tmdt.shoes_store_api.entities.Order;

public interface PaymentService {
    String paymentByPaypal(Order order);
    boolean captureTransactionPaypal(String paypalOrderId, String payerId);
}

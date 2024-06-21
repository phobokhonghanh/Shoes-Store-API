package fit.edu.tmdt.shoes_store_api.repository;

import fit.edu.tmdt.shoes_store_api.entities.Order;
import fit.edu.tmdt.shoes_store_api.entities.PaymentMethod;
import fit.edu.tmdt.shoes_store_api.entities.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepo extends JpaRepository<Order,Long> , JpaSpecificationExecutor<Order> {

    Order findByPaymentMethodOrderId(String paymentOrderId);

    Order findByCode(String code);
}

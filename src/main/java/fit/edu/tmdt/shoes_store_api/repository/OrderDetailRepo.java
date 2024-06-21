package fit.edu.tmdt.shoes_store_api.repository;

import fit.edu.tmdt.shoes_store_api.entities.Order;
import fit.edu.tmdt.shoes_store_api.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderDetailRepo extends JpaRepository<OrderDetail,Long> {
}

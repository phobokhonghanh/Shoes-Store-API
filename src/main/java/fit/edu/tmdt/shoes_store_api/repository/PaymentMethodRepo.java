package fit.edu.tmdt.shoes_store_api.repository;

import fit.edu.tmdt.shoes_store_api.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PaymentMethodRepo extends JpaRepository<PaymentMethod,Long>, JpaSpecificationExecutor<PaymentMethod> {

    List<PaymentMethod> getAllByStatusId(String id);
}

package fit.edu.tmdt.shoes_store_api.repository;

import fit.edu.tmdt.shoes_store_api.entities.Account;
import fit.edu.tmdt.shoes_store_api.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepo extends JpaRepository<Account,Long> , JpaSpecificationExecutor<Account> {
    Account findByEmailAndStatusId(String email, String status);

    Account findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}

package fit.edu.tmdt.shoes_store_api.repository;

import fit.edu.tmdt.shoes_store_api.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Account,Long> {
    Account findByEmailAndStatusId(String email, String status);

    Account findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}

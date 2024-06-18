package fit.edu.tmdt.shoes_store_api.repository;

import fit.edu.tmdt.shoes_store_api.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TypeRepo extends JpaRepository<Type,Long>, JpaSpecificationExecutor<Type> {
}

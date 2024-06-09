package fit.edu.tmdt.shoes_store_api.repository;

import fit.edu.tmdt.shoes_store_api.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepo extends JpaRepository<Size,Long> {
}

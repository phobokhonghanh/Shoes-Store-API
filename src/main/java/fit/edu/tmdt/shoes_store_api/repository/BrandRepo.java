package fit.edu.tmdt.shoes_store_api.repository;

import fit.edu.tmdt.shoes_store_api.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepo extends JpaRepository<Brand,Long> {
}

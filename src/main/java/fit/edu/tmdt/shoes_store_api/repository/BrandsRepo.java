package fit.edu.tmdt.shoes_store_api.repository;

import fit.edu.tmdt.shoes_store_api.entities.Brands;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandsRepo extends JpaRepository<Brands,Integer> {
}

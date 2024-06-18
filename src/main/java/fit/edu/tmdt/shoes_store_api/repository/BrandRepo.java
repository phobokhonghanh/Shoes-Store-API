package fit.edu.tmdt.shoes_store_api.repository;

import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandResponse;
import fit.edu.tmdt.shoes_store_api.entities.Brand;
import fit.edu.tmdt.shoes_store_api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BrandRepo extends JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand> {

    List<Brand> findAllByStatusId(String status);
}

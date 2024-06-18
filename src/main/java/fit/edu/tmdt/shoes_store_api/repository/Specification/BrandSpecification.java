package fit.edu.tmdt.shoes_store_api.repository.Specification;

import fit.edu.tmdt.shoes_store_api.entities.Brand;
import fit.edu.tmdt.shoes_store_api.entities.Product;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class BrandSpecification {
    public static Specification<Brand> containsTextInField(String keyword, String attribute) {
        return (root, query, builder) -> builder.like(root.get(attribute), "%" + keyword + "%");
    }
}

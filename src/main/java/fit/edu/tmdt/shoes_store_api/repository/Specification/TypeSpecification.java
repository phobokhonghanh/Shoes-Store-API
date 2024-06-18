package fit.edu.tmdt.shoes_store_api.repository.Specification;

import fit.edu.tmdt.shoes_store_api.entities.Brand;
import fit.edu.tmdt.shoes_store_api.entities.Type;
import org.springframework.data.jpa.domain.Specification;

public class TypeSpecification {
    public static Specification<Type> containsTextInField(String keyword, String attribute) {
        return (root, query, builder) -> builder.like(root.get(attribute), "%" + keyword + "%");
    }
}

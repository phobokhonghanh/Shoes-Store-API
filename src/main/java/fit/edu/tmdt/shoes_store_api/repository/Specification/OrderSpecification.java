package fit.edu.tmdt.shoes_store_api.repository.Specification;

import fit.edu.tmdt.shoes_store_api.entities.Order;
import fit.edu.tmdt.shoes_store_api.entities.Type;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {
    public static Specification<Order> containsTextInField(String keyword, String attribute) {
        return (root, query, builder) -> {
            if (keyword == null || keyword.isEmpty()) {
                return builder.conjunction(); // No filtering
            }
            return builder.like(root.get(attribute), "%" + keyword + "%");
        };
    }
}

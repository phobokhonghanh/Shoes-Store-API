package fit.edu.tmdt.shoes_store_api.repository.Specification;

import fit.edu.tmdt.shoes_store_api.entities.Product;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class GenericSpecification {
    public static Specification<Product> containsTextInField(String keyword, String attribute) {
        return (root, query, builder) -> builder.like(root.get(attribute), "%" + keyword + "%");
    }

    public static Specification<Product> containsKeywordInMultipleAttribute(String keyword, String... attributes) {
        return (root, query, builder) -> {
            Specification<Product> spec = null;
            for (String field : attributes) {
                if (spec == null) {
                    spec = containsTextInField(field, keyword);
                } else {
                    spec = spec.or(containsTextInField(field, keyword));
                }
            }
            return spec.toPredicate(root, query, builder);
        };
    }

    public static Specification<Product> findFilterInMultipleAttribute(String filter, String... attributes) {
        return (root, query, builder) -> {
            Specification<Product> spec = null;
            for (String field : attributes) {
                if (spec == null) {
                    spec = findByAttribute(field, filter);
                } else {
                    spec = spec.or(findByAttribute(field, filter));
                }
            }
            return spec.toPredicate(root, query, builder);
        };
    }

    public static Specification<Product> findByAttribute(String filter, String attribute) {
        return (root, query, builder) -> builder.equal(root.get(attribute), filter);
    }

    public static Specification<Product> joinAttribute(Object active, String attribute) {
        return (root, query, builder) -> {
            if (active != null) {
                Join<Object, Object> join = root.join(attribute);
                return builder.equal(join.get("id"), active);
            }
            return null;
        };
    }
}

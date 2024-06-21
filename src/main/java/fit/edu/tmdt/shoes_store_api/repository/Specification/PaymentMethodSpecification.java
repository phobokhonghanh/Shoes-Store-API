package fit.edu.tmdt.shoes_store_api.repository.Specification;

import fit.edu.tmdt.shoes_store_api.entities.Account;
import fit.edu.tmdt.shoes_store_api.entities.PaymentMethod;
import fit.edu.tmdt.shoes_store_api.entities.Product;
import fit.edu.tmdt.shoes_store_api.entities.Type;
import org.springframework.data.jpa.domain.Specification;

public class PaymentMethodSpecification {
    public static Specification<PaymentMethod> containsTextInField(String keyword, String attribute) {
        return (root, query, builder) -> builder.like(root.get(attribute), "%" + keyword + "%");
    }

    public static Specification<PaymentMethod> containsKeywordInMultipleAttribute(String keyword, String... attributes) {
        return (root, query, builder) -> {
            Specification<PaymentMethod> spec = null;
            for (String field : attributes) {
                if (spec == null) {
                    spec = containsTextInField(keyword, field);
                } else {
                    spec = spec.or(containsTextInField(keyword, field));
                }
            }
            return spec.toPredicate(root, query, builder);
        };
    }
}

package fit.edu.tmdt.shoes_store_api.dto.Statistical;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticalResponse implements Serializable {
    private Integer month;
    private BigDecimal sum;
}


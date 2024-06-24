package fit.edu.tmdt.shoes_store_api.dto.Statistical;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticalOtherResponse implements Serializable {
    private Integer month;
    private Long sum;
}


package fit.edu.tmdt.shoes_store_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupportDTO {
    private String id;
    private String supportValue;
    private String supportInfo;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}

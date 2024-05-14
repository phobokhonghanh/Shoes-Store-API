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
public class LogsDTO {
    private Integer idLog;
    private Integer idUser;
    private String src;
    private String content;
    private String idAddress;
    private String webBrowser;
    private LocalDateTime createAt;
    private String status;
    private SupportDTO supportLevel;

}

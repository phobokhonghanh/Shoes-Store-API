package fit.edu.tmdt.shoes_store_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "logs")
public class Logs {
    @Id
    @Column(name = "id_log")
    private Integer idLog;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "src")
    private String src;

    @Column(name = "content")
    private String content;

    @Column(name = "id_address")
    private String idAddress;

    @Column(name = "web_browser")
    private String webBrowser;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "support_level")
    private Support supportLevel;
}

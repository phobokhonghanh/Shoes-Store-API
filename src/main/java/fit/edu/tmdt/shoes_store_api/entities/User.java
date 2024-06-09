package fit.edu.tmdt.shoes_store_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "username", nullable = false, length = 255)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "fullname", nullable = false, length = 255)
    private String fullname;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "phone", nullable = false, length = 255)
    private String phone;

    @Column(name = "gender", nullable = false, length = 1)
    private String gender;

    @Column(name = "token", length = 255)
    private String token;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "support_status", referencedColumnName = "id", nullable = false)
    private Support status;

    @ManyToOne
    @JoinColumn(name = "support_role", referencedColumnName = "id", nullable = false)
    private Support role;
}
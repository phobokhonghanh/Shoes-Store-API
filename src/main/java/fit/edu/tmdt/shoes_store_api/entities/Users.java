package fit.edu.tmdt.shoes_store_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "path_img_avatar")
    private String pathImgAvatar;

    @Column(name = "otp")
    private String otp;
    @ManyToOne
    @JoinColumn(name = "support_role")
    private Support supportRole;
    @ManyToOne
    @JoinColumn(name = "support_status")
    private Support supportStatus;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;
}

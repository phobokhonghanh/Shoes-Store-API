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
public class UsersDTO {
    private Integer idUser;
    private String email;
    private String password;
    private String fullname;
    private String phone;
    private String pathImgAvatar;
    private String otp;
    private SupportDTO supportRole;
    private SupportDTO supportStatus;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}

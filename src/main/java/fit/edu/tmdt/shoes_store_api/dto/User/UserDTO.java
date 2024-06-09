package fit.edu.tmdt.shoes_store_api.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String gender;
    private String token;
    private String avatar;
    private String status;
    private String role;
}
package fit.edu.tmdt.shoes_store_api.dto.Authen;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String message;
    private String token;
    private String tokenType = "Bearer";
    private Long id;
    private String fullname;
    private List<String> roles;
}

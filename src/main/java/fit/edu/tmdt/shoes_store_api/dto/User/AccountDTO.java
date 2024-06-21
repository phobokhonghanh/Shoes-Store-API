package fit.edu.tmdt.shoes_store_api.dto.User;

import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String gender;
    private String token;
    private String avatar;
    private SupportDTO status;
    private SupportDTO role;

    public AccountDTO(Long id) {
        this.id = id;
    }
}
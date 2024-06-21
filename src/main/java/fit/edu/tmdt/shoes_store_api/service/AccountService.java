package fit.edu.tmdt.shoes_store_api.service;

import fit.edu.tmdt.shoes_store_api.dto.Authen.LoginDTO;
import fit.edu.tmdt.shoes_store_api.dto.Authen.LoginResponse;
import fit.edu.tmdt.shoes_store_api.dto.User.AccountDTO;
import fit.edu.tmdt.shoes_store_api.dto.User.UserResponse;
import fit.edu.tmdt.shoes_store_api.entities.Account;
import org.springframework.data.domain.Page;

public interface AccountService {
    LoginResponse login(LoginDTO loginDTO);

    String register(AccountDTO accountDTO);

    String checkToken(Long id, String otp, boolean resetPassword);

    boolean resetToken(Long id, boolean resetPassword);

    boolean existsEmail(String email);

    Account findByUsername(String username);

    UserResponse findById(Long id);

    boolean existsUsername(String username);

    Long forgotPassword(String username, String email);
    Page<UserResponse> getAll(Integer pageNo, Integer pageSize, String search);

    UserResponse updateAccount(AccountDTO accountDTO);
    UserResponse updateRole(Long id, boolean upRole);
    UserResponse updateStatus(Long id, boolean lock);

}

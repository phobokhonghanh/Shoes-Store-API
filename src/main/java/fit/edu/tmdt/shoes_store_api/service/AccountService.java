package fit.edu.tmdt.shoes_store_api.service;

import fit.edu.tmdt.shoes_store_api.dto.Authen.LoginDTO;
import fit.edu.tmdt.shoes_store_api.dto.Authen.LoginResponse;
import fit.edu.tmdt.shoes_store_api.dto.User.UserDTO;
import fit.edu.tmdt.shoes_store_api.entities.Account;

public interface AccountService {
    LoginResponse login(LoginDTO loginDTO);
    String register (UserDTO userDTO);
    String checkToken(Long id, String otp, boolean resetPassword);
    boolean resetToken (Long id, boolean resetPassword);
    boolean existsEmail(String email);
    public Account findByUsername(String username);
    boolean existsUsername(String username);
    Long forgotPassword(String username, String email);
}

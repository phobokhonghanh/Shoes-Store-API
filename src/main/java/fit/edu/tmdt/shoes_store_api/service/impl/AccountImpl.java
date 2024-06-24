package fit.edu.tmdt.shoes_store_api.service.impl;

import fit.edu.tmdt.shoes_store_api.Utils.ImplUtil;
import fit.edu.tmdt.shoes_store_api.config.token.JwtTokenUtils;
import fit.edu.tmdt.shoes_store_api.constant.Message;
import fit.edu.tmdt.shoes_store_api.dto.Support.Role;
import fit.edu.tmdt.shoes_store_api.dto.Support.Status;
import fit.edu.tmdt.shoes_store_api.convert.ConvertBase;
import fit.edu.tmdt.shoes_store_api.dto.Authen.LoginDTO;
import fit.edu.tmdt.shoes_store_api.dto.Authen.LoginResponse;
import fit.edu.tmdt.shoes_store_api.dto.Support.SupportDTO;
import fit.edu.tmdt.shoes_store_api.dto.User.AccountDTO;
import fit.edu.tmdt.shoes_store_api.dto.User.UserResponse;
import fit.edu.tmdt.shoes_store_api.entities.Account;
import fit.edu.tmdt.shoes_store_api.entities.Support;
import fit.edu.tmdt.shoes_store_api.repository.Specification.AccountSpecification;
import fit.edu.tmdt.shoes_store_api.repository.UserRepo;
import fit.edu.tmdt.shoes_store_api.service.AccountService;
import fit.edu.tmdt.shoes_store_api.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class AccountImpl implements AccountService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ConvertBase convertBase;
    @Autowired
    private ImplUtil implUtil;
    @Autowired
    private MailService emailService;
    @Autowired
    private final JwtTokenUtils jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public void deleteAccount(String email, String status) {
        Account account = userRepo.findByEmailAndStatusId(email, status);
        // nếu tồn tại thì xóa tài khoản đó đi
        if (account != null) {
            userRepo.delete(account);
        }
    }

    @Override
    public String register(AccountDTO accountDTO) {
        String email = accountDTO.getEmail();
        String username = accountDTO.getUsername();
        // xóa tài khoản đã tồn tại (trùng email nhưng chưa verify)
        deleteAccount(email, Status.VERIFY);

        // kiểm tra email và phone
        if (!existsEmail(email) && !existsUsername(username)) {
            // set trạng thái tài khoản
            SupportDTO status = new SupportDTO(Status.VERIFY);
            accountDTO.setStatus(status);
            // tạo otp và set vào account
            accountDTO.setToken(implUtil.renderOTP());
            // set trạng thái sang verify
            // mã hóa password
            accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
            // set role là client
            SupportDTO role = new SupportDTO(Role.CLIENT);
            if (accountDTO.getRole() != null) {
                if (accountDTO.getRole().equals(Role.ADMIN)) {
                    role.setId(Role.ADMIN);
                }
            }
            accountDTO.setRole(role);
            // tạo tài khoản
            Account account = convertBase.convert(accountDTO, Account.class);
            Account accountSave = userRepo.save(account);
            // send email
            Map<String, Object> attributes = Map.of(
                    "token", accountSave.getToken(),
                    "link", Message.EMAIL_LINK_OTP + accountSave.getId());
            emailService.sendVerificationToken(email, Message.EMAIL_SUBJECT_REGISTER, attributes);

            return accountSave.getId().toString();
        }
        return null;
    }

    @Override
    public String checkToken(Long id, String otp, boolean resetPassword) {
        Optional<Account> optional = userRepo.findById(id);
        String rs = "";
        if (optional.isPresent()) {
            Account account = optional.get();
            if (account.getToken().equals(otp)) {
                account.setToken(null);
                Support support = new Support(Status.UNLOCK);
                account.setStatus(support);
                userRepo.save(account);
                // reset password
                if (resetPassword) {
                    rs = jwtTokenUtil.generateTokenMin(account);
                }
                return rs;
            }
        }
        return null;
    }

    @Override
    public boolean resetToken(Long id, boolean resetPassword) {
        Optional<Account> optional = userRepo.findById(id);
        if (optional.isPresent()) {
            Account account = optional.get();
            account.setToken(implUtil.renderOTP());
            String subject = Message.EMAIL_SUBJECT_REGISTER;
            String link = Message.EMAIL_LINK_OTP + account.getId();
            // reset password
            if (resetPassword) {
                subject = Message.EMAIL_SUBJECT_RESET_PASSWORD;
                link += "?reset-password=true";
            }
            Map<String, Object> attributes = Map.of(
                    "token", account.getToken(),
                    "link", link
            );
            userRepo.save(account);
            emailService.sendVerificationToken(account.getEmail(), subject, attributes);
            return true;
        }
        return false;
    }

    public boolean sendOTPResetPassword(Long accountId) {
        Optional<Account> optional = userRepo.findById(accountId);
        if (optional.isPresent()) {
            Account account = optional.get();
            String otp = implUtil.renderOTP();
            account.setToken(otp);
            Map<String, Object> attributes = Map.of(
                    "token", otp,
                    "link", Message.EMAIL_LINK_OTP + account.getId() + "?reset-password=true");
            userRepo.save(account);
            emailService.sendVerificationToken(account.getEmail(), Message.EMAIL_SUBJECT_RESET_PASSWORD, attributes);
            return true;
        }
        return false;
    }

    @Override
    public Long forgotPassword(String username, String email) {
        if (existsUsername(username) && existsEmail(email)) {
            Account account = findByUsername(username);
            if (account.getStatus().getId().equalsIgnoreCase(Status.LOCK)) {
                return null;
            }
            if (sendOTPResetPassword(account.getId())) {
                return account.getId();
            }
        }
        return null;
    }

    @Override
    public Page<UserResponse> getAll(Integer pageNo, Integer pageSize, String search) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Specification<Account> spec = Specification.where(AccountSpecification.containsKeywordInMultipleAttribute(search, "username", "phone", "email", "fullname"));

        Page<Account> accountEntity = userRepo.findAll(spec, pageable);
        List<UserResponse> userDTO = convertBase.toListConvert(accountEntity.getContent(), UserResponse.class);
        return new PageImpl<>(userDTO, pageable, accountEntity.getTotalElements());
    }

    @Override
    public UserResponse updateAccount(AccountDTO accountDTO) {
        Account account = getAccountId(accountDTO.getId());
        if (account == null) return null;
        implUtil.updateFieldIfNotNull(accountDTO.getFullname(), account::setFullname);
        implUtil.updateFieldIfNotNull(accountDTO.getPhone(), account::setPhone);
        implUtil.updateFieldIfNotNull(accountDTO.getGender(), account::setGender);
        implUtil.updateFieldIfNotNull(accountDTO.getAvatar(), account::setAvatar);
        Account accountSave = userRepo.save(account);
        return convertBase.convert(accountSave, UserResponse.class);
    }

    @Override
    public UserResponse updateRole(Long id, boolean upRole) {
        Account account = getAccountId(id);
        if (account == null) {
            return null;
        }
        Support support = new Support(Role.ADMIN);
        if (upRole) {
            support.setId(Role.CLIENT);
        }
        account.setRole(support);
        Account accountSave = userRepo.save(account);
        return convertBase.convert(accountSave, UserResponse.class);
    }

    @Override
    public UserResponse updateStatus(Long id, boolean lock) {
        Account account = getAccountId(id);
        if (account == null) {
            return null;
        }
        Support support = new Support(Status.UNLOCK);
        if (lock) {
            support.setId(Status.LOCK);
        }
        account.setStatus(support);
        Account accountSave = userRepo.save(account);
        return convertBase.convert(accountSave, UserResponse.class);
    }

    public Account getAccountId(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        try {
            Account account = findByUsername(loginDTO.getUsername());

            if (account == null) {
                return LoginResponse.builder()
                        .message(Message.ACCOUNT_NOT_EXIST)
                        .build();
            }
            if (account.getStatus().getId().equalsIgnoreCase(Status.VERIFY)) {
                return LoginResponse.builder()
                        .message(Message.ACCOUNT_NOT_VERIFY)
                        .build();
            }
            if (account.getStatus().getId().equalsIgnoreCase(Status.LOCK)) {
                return LoginResponse.builder()
                        .message(Message.ACCOUNT_LOCKED)
                        .build();
            }
            if (!passwordEncoder.matches(loginDTO.getPassword(), account.getPassword())) {
                return LoginResponse.builder()
                        .message(Message.ACCOUNT_WRONG_PASSWORD)
                        .build();
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    account.getUsername(), loginDTO.getPassword(),
                    account.getAuthorities()
            );
            authenticationManager.authenticate(authenticationToken);
            String token = jwtTokenUtil.generateToken(account);
            return LoginResponse.builder()
                    .message(Message.LOGIN_SUCCESS)
                    .token(token)
                    .id(account.getId())
                    .fullname(account.getFullname())
                    .roles(account.getAuthorities().stream().map(item -> item.getAuthority()).toList())
                    .build();
        } catch (Exception e) {
            log.warn("LOGIN");
            log.warn(e);
            return LoginResponse.builder()
                    .message(Message.LOGIN_FAILED)
                    .build();
        }
    }

    @Override
    public Account findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public UserResponse findById(Long id) {
        Account account = getAccountId(id);
        if (account == null) {
            return null;
        }
        return convertBase.convert(account, UserResponse.class);
    }

    @Override
    public boolean existsEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public boolean existsUsername(String username) {
        return userRepo.existsByUsername(username);
    }


}

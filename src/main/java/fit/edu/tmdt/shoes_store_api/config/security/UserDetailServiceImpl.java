package fit.edu.tmdt.shoes_store_api.config.security;

import fit.edu.tmdt.shoes_store_api.constant.Status;
import fit.edu.tmdt.shoes_store_api.entities.Account;
import fit.edu.tmdt.shoes_store_api.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account userEntity = userRepo.findByUsername(username);
        //tìm trong dtb xem user có phone đó có tồn tại hay khong
        if (userEntity == null) throw new UsernameNotFoundException(username + " không tồn tại trong database");
        if (userEntity.getStatus().getId().equalsIgnoreCase(Status.LOCK)) {
            throw new RuntimeException("[Error] Account locked");
        }
        //tạo grantedAuthority với roleNames tương ứng
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(userEntity.getRole().getValue()));
        //tao doi tuong userdetail
        UserDetails userDetails = User.withUsername(userEntity.getUsername()).password(userEntity.getPassword()).authorities(grantedAuthorities).build();
        return userDetails;
    }
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        return null;
    }
}

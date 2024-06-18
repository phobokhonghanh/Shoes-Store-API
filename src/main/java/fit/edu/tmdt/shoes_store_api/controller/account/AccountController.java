package fit.edu.tmdt.shoes_store_api.controller.account;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.constant.Message;
import fit.edu.tmdt.shoes_store_api.dto.Authen.LoginDTO;
import fit.edu.tmdt.shoes_store_api.dto.Authen.LoginResponse;
import fit.edu.tmdt.shoes_store_api.dto.Brand.BrandResponse;
import fit.edu.tmdt.shoes_store_api.dto.User.UserDTO;
import fit.edu.tmdt.shoes_store_api.dto.User.UserResponse;
import fit.edu.tmdt.shoes_store_api.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("${api.prefix}")
@Log4j2
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/account/{id}")
    public ResponseEntity<UserResponse> getAccount(@PathVariable Long id) {
        return ResponseUtil.getResponse(accountService.findById(id), OK);
    }

    @PatchMapping("/account/{id}")
    public ResponseEntity<UserResponse> updateAccount(@RequestBody UserDTO userDTO) {
        return ResponseUtil.getResponse(accountService.updateAccount(userDTO), OK);
    }

    @GetMapping("/admin/account/all")
    public ResponseEntity<Page<UserResponse>> getAll(@RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer pageSize,
                                                     @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        return ResponseUtil.getResponse(accountService.getAll(pageNo, pageSize, search), OK);
    }

    @PatchMapping("/admin/account/status/{id}")
    public ResponseEntity updateStatus(@PathVariable Long id, @RequestParam(name = "is-lock", defaultValue = "true") boolean isLock) {
        return ResponseUtil.getResponse(accountService.updateStatus(id, isLock), OK);
    }

    @PatchMapping("/admin/account/role/{id}")
    public ResponseEntity updateRole(@PathVariable Long id, @RequestParam(name = "is-low", defaultValue = "true") boolean isLow) {
        return ResponseUtil.getResponse(accountService.updateRole(id, isLow), OK);
    }
}

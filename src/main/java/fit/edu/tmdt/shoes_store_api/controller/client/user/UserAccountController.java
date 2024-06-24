package fit.edu.tmdt.shoes_store_api.controller.client.user;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.dto.User.AccountDTO;
import fit.edu.tmdt.shoes_store_api.dto.User.UserResponse;
import fit.edu.tmdt.shoes_store_api.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("${api.prefix}/user-api/")
@Log4j2
public class UserAccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/account/{id}")
    public ResponseEntity<UserResponse> getAccount(@PathVariable Long id) {
        return ResponseUtil.getResponse(accountService.findById(id), OK);
    }

    @PatchMapping("/account/{id}")
    public ResponseEntity<UserResponse> updateAccount(@RequestBody AccountDTO accountDTO) {
        return ResponseUtil.getResponse(accountService.updateAccount(accountDTO), OK);
    }
}

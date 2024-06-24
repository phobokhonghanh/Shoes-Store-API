package fit.edu.tmdt.shoes_store_api.controller.auth;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.constant.Message;
import fit.edu.tmdt.shoes_store_api.dto.Authen.LoginDTO;
import fit.edu.tmdt.shoes_store_api.dto.Authen.LoginResponse;
import fit.edu.tmdt.shoes_store_api.dto.User.AccountDTO;
import fit.edu.tmdt.shoes_store_api.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("${api.prefix}")
@Log4j2
public class AuthController {
    @Autowired
    AccountService accountService;

    @PostMapping("/admin/auth/register")
    public ResponseEntity createAdmin(@RequestBody AccountDTO accountDTO) {
        log.info("Register admin");
        String response = accountService.register(accountDTO);
        return ResponseUtil.getResponseWithMessage(response, Message.ACCOUNT_EXIST, OK);
    }
}

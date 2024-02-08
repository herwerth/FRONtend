package hu.progmasters.backend.controller;

import hu.progmasters.backend.dto.*;
import hu.progmasters.backend.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@Slf4j
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/save")
    public ResponseEntity<AccountInfo> saveAccount(@Valid @RequestBody RegisterAccountCommand command) {
        log.info("Http request POST / /api/accounts with data: " + command.toString());
        AccountInfo accountInfo = accountService.saveAccount(command);
        return new ResponseEntity<>(accountInfo, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<AccountInfo> updateAccount(@Valid @RequestBody UpdateAccountCommand command) {
        log.info("Http request PUT / /api/accounts/{accountId} with data: " + command.toString());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountInfo accountInfo = accountService.updateAccount(
                accountService.loadUserByUsername((String) authentication.getPrincipal()), command);
        return new ResponseEntity<>(accountInfo, HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Void> deleteAccount(@PathVariable("accountId") Long accountId) {
        log.info("Http request DELETE / /api/accounts/{accountId} with id: " + accountId);
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<List<AccountInfo>> getAllAccount() {
        log.info("Http request GET / /api/accounts/all");
        List<AccountInfo> accountInfoList = accountService.getAllAccount();
        return new ResponseEntity<>(accountInfoList, HttpStatus.OK);
    }

    @GetMapping("/me")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<AccountInfoWithPosts> getLoggedInAccountWithPosts() {
        log.info("Http reguest GET / /api/accounts/me");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountInfoWithPosts accountInfoWithPosts = accountService.getMyAccountWithPosts(
                accountService.loadUserByUsername((String) authentication.getPrincipal()));
        return new ResponseEntity<>(accountInfoWithPosts, HttpStatus.OK);
    }

    @PutMapping("/changepassword")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordCommand command) {
        log.info("Http request PUT / /api/accounts/changepassword");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        accountService.changePassword(accountService.loadUserByUsername((String) authentication.getPrincipal()), command);
        return new ResponseEntity<>("Jelszó sikeresen megváltoztatva!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        AuthenticationResponseDto responseDto = accountService.login(authenticationRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshAccessToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        RefreshTokenResponse tokenResponse = accountService.refreshAccessToken(refreshTokenRequest);
        return new ResponseEntity<>(tokenResponse, HttpStatus.ACCEPTED);
    }
}

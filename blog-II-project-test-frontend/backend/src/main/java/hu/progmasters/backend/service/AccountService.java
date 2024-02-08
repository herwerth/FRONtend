package hu.progmasters.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import hu.progmasters.backend.domain.Account;
import hu.progmasters.backend.domain.Role;
import hu.progmasters.backend.domain.Status;
import hu.progmasters.backend.dto.*;
import hu.progmasters.backend.exceptionhandling.*;
import hu.progmasters.backend.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AccountService implements UserDetailsService {

    private AccountRepository accountRepository;

    private LoginService loginService;

    private ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, LoginService loginService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.loginService = loginService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public AccountInfo saveAccount(RegisterAccountCommand command) {
        String accountUsername = accountRepository.findAccountUsername(command.getUsername());
        checkAccountUsername(accountUsername, command.getUsername());

        String accountEmail = accountRepository.findAccountEmail(command.getEmail());
        checkAccountEmail(accountEmail, command.getEmail());

        String accountDisplayName = accountRepository.findAccountDisplayName(command.getDisplayName());
        checkAccountDisplayName(accountDisplayName, command.getDisplayName());

        checkPasswordFormat(command);

        Account account = modelMapper.map(command, Account.class);
        account.setDateOfRegistration(LocalDate.now());
        account.setUserStatus(Status.ACTIVE);
        account.setPassword(passwordEncoder.encode(command.getPassword()));
        account.setRole(List.of(Role.ROLE_USER));
        accountRepository.save(account);
        return modelMapper.map(account, AccountInfo.class);
    }

    public AccountInfo updateAccount(UserDetails userDetails, UpdateAccountCommand command) {
        Account account = accountRepository.findAccountByUsername(userDetails.getUsername());
        if (command.getUsername().isEmpty()) {
            command.setUsername(account.getUsername());
        } else {
            String accountUsername = accountRepository.findAccountUsername(command.getUsername());
            checkAccountUsername(accountUsername, command.getUsername());
        }

        if (command.getEmail().isEmpty()) {
            command.setEmail(account.getEmail());
        } else {
            String accountEmail = accountRepository.findAccountEmail(command.getEmail());
            checkAccountEmail(accountEmail, command.getEmail());
        }

        if (command.getDisplayName().isEmpty()) {
            command.setDisplayName(account.getDisplayName());
        } else {
            String accountDisplayName = accountRepository.findAccountDisplayName(command.getDisplayName());
            checkAccountDisplayName(accountDisplayName, command.getDisplayName());
        }

        modelMapper.map(command, account);
        AccountInfo accountInfo = modelMapper.map(account, AccountInfo.class);
        accountInfo.setRole(account.getRole());
        return accountInfo;
    }

    public void deleteAccount(Long accountId) {
        Account account = findAccountById(accountId);
        account.setDisplayName(null);
        account.setPassword(null);
        account.setUsername(null);
        account.setUserStatus(Status.DELETED);
    }

    public Account findAccountById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundByIdException(id);
        }
        return optionalAccount.get();
    }

    public List<AccountInfo> getAllAccount() {
        return accountRepository.getAllAccount().stream()
                .map(account -> modelMapper.map(account, AccountInfo.class))
                .collect(Collectors.toList());
    }

    public AccountInfoWithPosts getMyAccountWithPosts(UserDetails userDetails) {
        Account account = accountRepository.findAccountByUsername(userDetails.getUsername());
        AccountInfoWithPosts accountInfoWithPosts = modelMapper.map(account, AccountInfoWithPosts.class);
        accountInfoWithPosts.setRole(account.getRole());
        List<PostInfo> postInfoList = account.getPosts().stream()
                .map(post -> {
                    PostInfo postInfo = new PostInfo();
                    postInfo.setId(post.getId());
                    postInfo.setDateOfPost(post.getDateOfPost());
                    postInfo.setPostTitle(post.getPostTitle());
                    postInfo.setPostBody(post.getPostBody());
                    postInfo.setPostStatus(post.getPostStatus().getDisplayName());
                    postInfo.setCommentStatus(post.getCommentStatus());
                    postInfo.setTag(post.getTag());
                    postInfo.setHighlighted(post.getHighlighted());
                    postInfo.setCategoryInfo(modelMapper.map(post.getCategory(), CategoryInfo.class));
                    return postInfo;
                })
                .collect(Collectors.toList());
        accountInfoWithPosts.setPostInfos(postInfoList);
        return accountInfoWithPosts;
    }

    public void changePassword(UserDetails userDetails, ChangePasswordCommand command) {
        Account account = accountRepository.findAccountByUsername(userDetails.getUsername());
        if (!passwordEncoder.matches(command.getOldPassword(), account.getPassword())) { // megadott régi jelszó nem helyes
            throw new OldPasswordNotCorrectException(command.getOldPassword());
        } else if (passwordEncoder.matches(command.getNewPassword(), account.getPassword())) { // új jelszó megegyezik a régi jelszóval
            throw new NewPasswordEqualsOldPasswordException(command.getNewPassword());
        } else if (!command.getNewPassword().equals(command.getNewPasswordAgain())) { // új jelszó és új jelszó újra nem egyezik meg
            throw new NewPasswordAndNewPasswordAgainNotEqualException(command.getNewPassword(), command.getNewPasswordAgain());
        }
        account.setPassword(passwordEncoder.encode(command.getNewPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUsername(username);
        if (account == null) {
            log.error("Account not found in the database");
            throw new UsernameNotFoundException("Account not found in the database");
        } else {
            log.info("Account found in the database: {}", username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.getRole().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoles())));
        return new User(account.getUsername(), account.getPassword(), authorities);
    }

    public Account getAccount(String username) {
        log.info("Fetching user: {}", username);
        return accountRepository.findAccountByUsername(username);
    }

    public AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequestDto) {
        return loginService.login(authenticationRequestDto);
    }

    public RefreshTokenResponse refreshAccessToken(RefreshTokenRequest refreshTokenRequest) {
        return loginService.refreshAccessToken(refreshTokenRequest);
    }

    private void checkAccountUsername(String accountUsername, String givenUsername) {
        if (accountUsername != null && accountUsername.equals(givenUsername)) {
            throw new AccountWithUsernameAlreadyExistsException(givenUsername);
        }
    }

    private void checkAccountEmail(String accountEmail, String givenEmail) {
        if (accountEmail != null && accountEmail.equals(givenEmail)) {
            throw new AccountWithEmailAlreadyExistsException(givenEmail);
        } else if (!isAccountEmailValid(givenEmail)) {
            throw new AccountEmailFormatInvalidException(givenEmail);
        }
    }

    private void checkAccountDisplayName(String accountDisplayName, String givenDisplayName) {
        if (accountDisplayName != null && accountDisplayName.equals(givenDisplayName)) {
            throw new AccountWithDisplayNameAlreadyExistsException(givenDisplayName);
        }
    }

    private void checkPasswordFormat(RegisterAccountCommand command) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])" +
                "(?=.*[A-Z])(?=\\S+$).{5,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        if (!pattern.matcher(command.getPassword()).matches()) {
            throw new PasswordFormatInvalidException(command.getPassword());
        }
    }

    private boolean isAccountEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}

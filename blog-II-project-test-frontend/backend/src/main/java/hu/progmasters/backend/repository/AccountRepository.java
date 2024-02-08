package hu.progmasters.backend.repository;

import hu.progmasters.backend.domain.Account;
import hu.progmasters.backend.dto.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select a.username from Account a where a.username = :username")
    String findAccountUsername(String username);

    @Query("select a.email from Account a where a.email = :email")
    String findAccountEmail(String email);

    @Query("select a.displayName from Account a where a.displayName = :displayName")
    String findAccountDisplayName(String displayName);

    @Query("select a from Account a where a.username = :username")
    Account findAccountByUsername(String username);

    @Query("select new hu.progmasters.backend.dto.AccountInfo(a.id, a.username, a.email, a.displayName, a.dateOfRegistration, a.userStatus) from Account a")
    List<AccountInfo> getAllAccount();
}
